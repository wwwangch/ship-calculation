package com.iscas.common.jredisearch.tools;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.RedisInfo;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import io.redisearch.*;
import io.redisearch.aggregation.AggregationBuilder;
import io.redisearch.aggregation.SortedField;
import io.redisearch.aggregation.reducers.Reducers;
import io.redisearch.client.AddOptions;
import io.redisearch.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/4 13:24
 * @since jdk1.8
 */
class JredisearchUtilsTest {

    private Client client = null;

    @BeforeEach
    public void before() {
        JedisConnection jedisConnection = new JedisStandAloneConnection();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setMaxIdle(10);
        configInfo.setMaxTotal(50);
        configInfo.setMaxWait(20000);
        RedisInfo redisInfo = new RedisInfo();
        redisInfo.setHost("172.16.10.160");
        redisInfo.setPort(31158);
//        redisInfo.setPwd("iscas");
        redisInfo.setTimeout(10000);
        configInfo.setRedisInfos(Arrays.asList(redisInfo));
        client = JredisearchUtils.initJredisearchClient(jedisConnection, configInfo, "test-index6");
    }

    /**
     * 测试获取连接
     */
    @Test
    public void testConnection() {
        System.out.println(client);
    }

    /**
     * 测试创建索引
     */
    @Test
    public void createIndex() {
        Schema schema = new Schema()
                .addTextField("title", 5.0)
                .addTextField("body", 1.0)
                .addNumericField("sc");
//        IndexDefinition def = new IndexDefinition()
//                .setPrefixes(new String[]{"item:", "goods:"})
//                .setFilter("@score>10");
        boolean flag = client.createIndex(schema, Client.IndexOptions.defaultOptions()/*.setDefinition(def)*/);
        Assertions.assertTrue(flag);
    }

    /**
     * 测试添加文档
     */
    @Test
    public void addDoc() {
//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "锄禾");
//        fields.put("type", "诗歌");
//        fields.put("body", "锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦。");
//        fields.put("score", 20);

//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "aaa");
//        fields.put("tp", "test");
//        fields.put("body", "aaaaaaa");
//        fields.put("score", 20);

//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "鹅");
//        fields.put("tp", "诗歌");
//        fields.put("body", "鹅鹅鹅，曲项向天歌。白毛浮绿水，红掌拨清波。");
//        fields.put("score", 100);

//        boolean b = client.addDocument("9", fields);
//        Assertions.assertTrue(b);

//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "赠汪伦");
//        fields.put("tp", "诗歌");
//        fields.put("body", "李白乘舟将欲行，忽闻岸上踏歌声。桃花潭水深千尺，不及汪伦送我情。");
//        fields.put("sc", 200);

//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "鹅");
//        fields.put("tp", "诗歌");
//        fields.put("body", "鹅鹅鹅，曲项向天歌。白毛浮绿水，红掌拨清波。");
//        fields.put("sc", 100);

//        Map<String, Object> fields = new HashMap<>();
//        fields.put("title", "aaa");
//        fields.put("tp", "test");
//        fields.put("body", "this is my test");
//        fields.put("sc", 100);

        Map<String, Object> fields = new HashMap<>();
        fields.put("title", "jredisearch");
        fields.put("tp", "test");
        fields.put("body", "在Redis之上实现了一个搜索引擎，但与其他Redis搜索库不同的是，它不使用诸如Sorted Sets之类的内部数据结构。反向索引存储为特殊的压缩数据类型，可实现快速索引和搜索速度，并减少内存占用。这还启用了更高级的功能，例如精确的词组匹配和文本查询的数字过滤，这是传统Redis搜索方法无法实现或无法实现的。");
        fields.put("sc", 150);
        boolean b = client.addDocument("id4", fields);
        Assertions.assertTrue(b);

    }


    /**
     * 插入中文文档
     * */
    @Test
    public void addCnDoc() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("title", "jredisearch");
        fields.put("tp", "test");
        fields.put("body", "在Redis之上实现了一个搜索引擎，但与其他Redis搜索库不同的是，它不使用诸如Sorted Sets之类的内部数据结构。反向索引存储为特殊的压缩数据类型，可实现快速索引和搜索速度，并减少内存占用。这还启用了更高级的功能，例如精确的词组匹配和文本查询的数字过滤，这是传统Redis搜索方法无法实现或无法实现的。");
        fields.put("sc", 150);
        Document document = new Document("cnId1", fields, 0.8, null);
        client.addDocument(document, new AddOptions().setLanguage("chinese"));
    }

    /**
     * 创建复杂查询
     */
    @Test
    public void search() {
        Query query = new Query("Redis 搜索引擎")
                .setLanguage("chinese")
                .addFilter(new Query.NumericFilter("sc", 0, 10000))
                .limit(0, 10000);
//        query.setNoStopwords();
        //没有值
//        query.setNoContent();

//        query.setWithScores();

//        query.setWithPayload();

        query.highlightFields("body");

        SearchResult searchResult = client.search(query);
        List<Document> docs = searchResult.docs;
        docs.forEach(System.out::println);
    }

    /**
     * 聚合查询
     */
    @Test
    public void aggregate() {
        AggregationBuilder builder = new AggregationBuilder()
                .groupBy("@body", Reducers.count().as("c"))
                .filter("@c>=0")
                .sortBy(10, SortedField.asc("@body"));
        AggregationResult aggregate = client.aggregate(builder);

        byte[] c = (byte[]) aggregate.getResults().get(0).get("c");
        System.out.println(new String(c));

        System.out.println(aggregate);
    }

    /**
     * 分词查询 先分词 在合并
     */
    @Test
    public void searchParticiple() {
        List<SearchResult> result = new ArrayList<>();
        //分词
        List<Term> termList = NLPTokenizer.segment("实现了一个搜索引擎");
//        List<Term> termList = StandardTokenizer.segment("搜索引擎");
        termList.stream().forEach(e -> {
            Query query = new Query(e.word)
                    .setLanguage("chinese")
                    .addFilter(new Query.NumericFilter("sc", 0, 10000))
                    .limit(0, 100);
            query.highlightFields("body");
            SearchResult search = client.search(query);
            result.add(search);
        });
        System.out.println(result);
    }

    /**
     * 推荐查询
     */
    @Test
    public void searchSuggest() {
        List<SearchResult> result = new ArrayList<>();
        //推荐
        List<String> suggest = new Suggester().suggest("实现了一个搜索引擎", 100);
        suggest.stream().forEach(e -> {
            Query query = new Query(e)
                    .setLanguage("chinese")
                    .addFilter(new Query.NumericFilter("sc", 0, 3000))
                    .limit(0, 1000);
            query.highlightFields("body");
            SearchResult search = client.search(query);
            result.add(search);
        });
        System.out.println(result);
    }
}