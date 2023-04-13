package com.iscas.common.rocksdb.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.RocksDBException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 17:14
 * @since jdk1.8
 */
class RocksDbServiceTest {

    private RocksDbService rocksDbService;

    @BeforeEach
    void before() throws RocksDBException {
        rocksDbService = new RocksDbService("d:/tmp/rocksdb");
    }

    @Test
    void addCfIfNotExist() throws RocksDBException {
        ColumnFamilyHandle test = rocksDbService.addCfIfNotExists("test");
        Assertions.assertNotNull(test);
    }

    @Test
    void deleteCfIfExists() throws RocksDBException {
        rocksDbService.deleteCfIfExists("test");
    }

    @Test
    void put() throws RocksDBException {
        rocksDbService.put("test", "key", "value");
    }

    @Test
    void batchPut() throws RocksDBException {
        rocksDbService.batchPut("test", new HashMap<>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});
    }

    @Test
    void delete() throws RocksDBException {
        rocksDbService.delete("test", "key");
    }

    @Test
    void get() throws RocksDBException {
        String value = rocksDbService.get("test", "key1");
        System.out.println(value);
        Assertions.assertNotNull(value);
    }

    @Test
    void multiGetAsMap() throws RocksDBException {
        Map<String, String> stringStringMap = rocksDbService.multiGetAsMap("test", List.of("key1", "key2", "key3"));
        System.out.println(stringStringMap);
    }

    @Test
    void multiGetValueAsList() throws RocksDBException {
        List<String> values = rocksDbService.multiGetValueAsList("test", List.of("key1", "key2"));
        System.out.println(values);
    }

    @Test
    void getAllKey() throws RocksDBException {
        List<String> test = rocksDbService.getAllKey("test");
        System.out.println(test);
    }

    @Test
    void getKeysFrom() throws RocksDBException {
        List<String> keysFrom = rocksDbService.getKeysFrom("test", "key1");
        System.out.println(keysFrom);
    }

    @Test
    void getAll() throws RocksDBException {
        Map<String, String> map = rocksDbService.getAll("test");
        System.out.println(map);
    }

    @Test
    void getCount() throws RocksDBException {
        int count = rocksDbService.getCount("test");
        System.out.println(count);
    }
}