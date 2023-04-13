package com.iscas.common.rocksdb.tools;

import org.rocksdb.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * rocksdb工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 15:00
 * @since jdk1.8
 */
public class RocksDbService {
    private RocksDB rocksDB;
    /**
     * 数据库列族(表)集合
     */
    private ConcurrentMap<String, ColumnFamilyHandle> columnFamilyHandleMap = new ConcurrentHashMap<>();

    private int GET_KEYS_BATCH_SIZE = 100000;

    private String rocksdbPath;

    public RocksDbService(String rocksdbPath) throws RocksDBException {
        this.rocksdbPath = rocksdbPath;
        init(rocksdbPath);
    }

    public void init(String rocksdbPath) throws RocksDBException {
        RocksDB.loadLibrary();
        Options options = new Options();
        // 如果数据库不存在则创建
        options.setCreateIfMissing(true);
        // 初始化所有已存在列族
        List<byte[]> cfArr = RocksDB.listColumnFamilies(options, rocksdbPath);
        //ColumnFamilyDescriptor集合
        List<ColumnFamilyDescriptor> columnFamilyDescriptors = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cfArr)) {
            for (byte[] cf : cfArr) {
                columnFamilyDescriptors.add(new ColumnFamilyDescriptor(cf, new ColumnFamilyOptions()));
            }
        } else {
            columnFamilyDescriptors.add(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, new ColumnFamilyOptions()));
        }
        DBOptions dbOptions = new DBOptions();
        dbOptions.setCreateIfMissing(true);
        // ColumnFamilyHandle集合
        List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
        rocksDB = RocksDB.open(dbOptions, rocksdbPath, columnFamilyDescriptors, columnFamilyHandles);
        for (int i = 0; i < columnFamilyDescriptors.size(); i++) {
            ColumnFamilyHandle columnFamilyHandle = columnFamilyHandles.get(i);
            String cfName = new String(columnFamilyDescriptors.get(i).getName(), StandardCharsets.UTF_8);
            columnFamilyHandleMap.put(cfName, columnFamilyHandle);
        }
        System.out.println("RocksDB init success!! path:" + rocksdbPath);
        System.out.println("cfNames:" + columnFamilyHandleMap.keySet());
    }

    /**
     * 列族，创建（如果不存在）
     */
    public ColumnFamilyHandle addCfIfNotExists(String cfName) throws RocksDBException {
        ColumnFamilyHandle columnFamilyHandle;
        if (!columnFamilyHandleMap.containsKey(cfName)) {
            columnFamilyHandle = rocksDB.createColumnFamily(new ColumnFamilyDescriptor(cfName.getBytes(), new ColumnFamilyOptions()));
            columnFamilyHandleMap.put(cfName, columnFamilyHandle);
            System.out.println("addCfIfNotExists success!! cfName:" + cfName);
        } else {
            columnFamilyHandle = columnFamilyHandleMap.get(cfName);
        }

        return columnFamilyHandle;
    }

    /**
     * 列族，删除（如果存在）
     */
    public void deleteCfIfExists(String cfName) throws RocksDBException {
        if (columnFamilyHandleMap.containsKey(cfName)) {
            rocksDB.dropColumnFamily(columnFamilyHandleMap.get(cfName));
            columnFamilyHandleMap.remove(cfName);
            System.out.println("deleteCfIfExists success!! cfName:" + cfName);
        } else {
           System.out.println("deleteCfIfExists containsKey!! cfName:" + cfName);
        }
    }

    /**
     * 增
     */
    public void put(String cfName, String key, String value) throws RocksDBException {
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        rocksDB.put(columnFamilyHandle, key.getBytes(), value.getBytes());
    }

    /**
     * 增（批量）
     */
    public void batchPut(String cfName, Map<String, String> map) throws RocksDBException {
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        WriteOptions writeOptions = new WriteOptions();
        WriteBatch writeBatch = new WriteBatch();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            writeBatch.put(columnFamilyHandle, entry.getKey().getBytes(), entry.getValue().getBytes());
        }
        rocksDB.write(writeOptions, writeBatch);
    }

    /**
     * 删
     */
    public void delete(String cfName, String key) throws RocksDBException {
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        rocksDB.delete(columnFamilyHandle, key.getBytes());
    }

    /**
     * 查
     */
    public String get(String cfName, String key) throws RocksDBException {
        String value = null;
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        byte[] bytes = rocksDB.get(columnFamilyHandle, key.getBytes());
        if (!ArrayUtils.isEmpty(bytes)) {
            value = new String(bytes, StandardCharsets.UTF_8);
        }
        return value;
    }

    /**
     * 查（多个键值对）
     */
    public Map<String, String> multiGetAsMap(String cfName, List<String> keys) throws RocksDBException {
        Map<String, String> map = new HashMap<>(keys.size());
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        List<ColumnFamilyHandle> columnFamilyHandles;
        List<byte[]> keyBytes = keys.stream().map(String::getBytes).collect(Collectors.toList());
        columnFamilyHandles = IntStream.range(0, keys.size()).mapToObj(i -> columnFamilyHandle).collect(Collectors.toList());
        Map<byte[], byte[]> bytesMap = rocksDB.multiGet(columnFamilyHandles, keyBytes);
        bytesMap.forEach((k, v) -> {
            map.put(new String(k, StandardCharsets.UTF_8), new String(v, StandardCharsets.UTF_8));
        });
        return map;
    }

    /**
     * 查（多个值）
     */
    public List<String> multiGetValueAsList(String cfName, List<String> keys) throws RocksDBException {
        List<String> values = new ArrayList<>(keys.size());
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName); //获取列族Handle
        List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
        List<byte[]> keyBytes = keys.stream().map(String::getBytes).collect(Collectors.toList());
        for (int i = 0; i < keys.size(); i++) {
            columnFamilyHandles.add(columnFamilyHandle);
        }
        Map<byte[], byte[]> bytesMap = rocksDB.multiGet(columnFamilyHandles, keyBytes);
        bytesMap.forEach((k, v) -> {
            values.add(new String(v, StandardCharsets.UTF_8));
        });
        return values;
    }

    /**
     * 查（所有键）
     */
    public List<String> getAllKey(String cfName) throws RocksDBException {
        List<String> list = new ArrayList<>();
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        try (RocksIterator rocksIterator = rocksDB.newIterator(columnFamilyHandle)) {
            for (rocksIterator.seekToFirst(); rocksIterator.isValid(); rocksIterator.next()) {
                list.add(new String(rocksIterator.key(), StandardCharsets.UTF_8));
            }
        }
        return list;
    }

    /**
     * 分片查（键）
     */
    public List<String> getKeysFrom(String cfName, String lastKey) throws RocksDBException {
        List<String> list = new ArrayList<>(GET_KEYS_BATCH_SIZE);
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        try (RocksIterator rocksIterator = rocksDB.newIterator(columnFamilyHandle)) {
            if (lastKey != null) {
                rocksIterator.seek(lastKey.getBytes(StandardCharsets.UTF_8));
                rocksIterator.next();
            } else {
                rocksIterator.seekToFirst();
            }
            // 一批次最多 GET_KEYS_BATCH_SIZE 个 key
            while (rocksIterator.isValid() && list.size() < GET_KEYS_BATCH_SIZE) {
                list.add(new String(rocksIterator.key(), StandardCharsets.UTF_8));
                rocksIterator.next();
            }
        }
        return list;
    }

    /**
     * 查（所有键值）
     */
    public Map<String, String> getAll(String cfName) throws RocksDBException {
        Map<String, String> map = new HashMap<>();
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        try (RocksIterator rocksIterator = rocksDB.newIterator(columnFamilyHandle)) {
            for (rocksIterator.seekToFirst(); rocksIterator.isValid(); rocksIterator.next()) {
                map.put(new String(rocksIterator.key(), StandardCharsets.UTF_8), new String(rocksIterator.value(), StandardCharsets.UTF_8));
            }
        }
        return map;
    }

    /**
     * 查总条数
     */
    public int getCount(String cfName) throws RocksDBException {
        int count = 0;
        // 获取列族Handle
        ColumnFamilyHandle columnFamilyHandle = addCfIfNotExists(cfName);
        try (RocksIterator rocksIterator = rocksDB.newIterator(columnFamilyHandle)) {
            for (rocksIterator.seekToFirst(); rocksIterator.isValid(); rocksIterator.next()) {
                count++;
            }
        }
        return count;
    }

}
