package com.example.initial_frame.service;

import com.alibaba.fastjson.JSONObject;
import com.example.initial_frame.common.es.ESUtils;
import com.example.initial_frame.common.es.InitClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EsTestService {


    /**
    * @Description TODO-SDC 判断索引是否存在
    * @Author  SunDC
    * @Date   2021/1/8 下午4:18
    * @Param
    * @Return
    * @Exception
    *
    */
    public void checkIndexExist(String index) throws IOException {
        ESUtils.checkIndexExist(index);
    }

    /**
     * @Description TODO-SDC 万能测试入口
     * @Author  SunDC
     * @Date   2021/1/8 下午4:18
     * @Param
     * @Return
     * @Exception
     *
     */
    public void master(Map map) throws IOException {
//        ESUtils.createIndex(map.get("indexName").toString(),false);
//        ESUtils.delIndex(map.get("indexName").toString());
    }


    public static void main(String[] args) throws Exception {
//        ESUtils.createIndex("sun",false);
//        Map map = new HashMap();
//        map.put("name","孙德超");
//        map.put("age",100);
//        map.put("msg","程序员");
//        //增
//        ESUtils.insert("sun","sundc","001",map );
//
//
//        map.put("name","孙德贵");
//        map.put("age",110);
//        map.put("msg","程序员");
//        //增
//        ESUtils.insert("sun","sundc","002",map );
//
//
//        map.put("name","孙德六");
//        map.put("age",130);
//        map.put("msg","程序员");
//        //增
//        ESUtils.insert("sun","sundc","003",map );
//
//
//        map.put("name","孙德八");
//        map.put("age",108);
//        map.put("msg","程序员");
//        //增
//        ESUtils.insert("sun","sundc","004",map );
//
//
//        map.put("name","孙钢珠");
//        map.put("age",101);
//        map.put("msg","程序员");
//        //增
//        ESUtils.insert("sun","sundc","005",map );
        //改
//        ESUtils.update("sun001","sundc","001",map );
        //查（单个）
//        JSONObject esDataById = ESUtils.getEsDataById("sun001", "sundc", "001");

    }




    /************************************************以下为高级查询案例****************************************/




    /**
    * @Description TODO-SDC 单条件查询
    * @Author  SunDC
    * @Date   2021/2/20 下午2:16
    * @Param
    * @Return
    * @Exception
    *
    */
    public Object queryData() throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        /**
         * 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
         */
//      match_phrase 完全匹配，不进行拆词
//		boolBuilder.must(QueryBuilders.matchPhraseQuery("name", "孙德超"));
//      match 进行拆词，然后将拆开的词进行 or 匹配，匹配到一个就可以
//		boolBuilder.must(QueryBuilders.matchQuery("name", "孙"));
//      多个字段进行搜索， 第一个字段：搜索的内容，会进行拆词， 后面一次写入需要 匹配的字段
        boolBuilder.must(QueryBuilders.multiMatchQuery("孙", "name"));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(2); // 获取记录数，默认10
//      第一个是获取字段，第二个是过滤的字段，默认获取全部
//		sourceBuilder.fetchSource(new String[] { "last_name", "age" }, new String[] {});
        sourceBuilder.fetchSource(new String[]{}, new String[]{});

        //索引，那个库
        SearchRequest searchRequest = new SearchRequest("sun");
        //类型type， 那个表
        searchRequest.types("sundc");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String data = JSONObject.toJSONString(sourceAsMap);
            System.out.println(data);
        }
        return searchHits;
    }



    /**
     * @Description TODO-SDC 多条件查询
     * @Author  SunDC
     * @Date   2021/2/20 下午2:16
     * @Param
     * @Return
     * @Exception
     *
     */
    public Object queryMoreData() throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        /**
         * 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
         */
//      match_phrase 完全匹配，不进行拆词
		boolBuilder.must(QueryBuilders.matchPhraseQuery("name", "孙德超"));
//      数字可以按照String进行匹配
//      boolBuilder.must(QueryBuilders.matchPhraseQuery("age", "100"));
        boolBuilder.must(QueryBuilders.rangeQuery("age").gte(99).lte(101));
//      日期范围
//      QueryBuilders.rangeQuery("test2").gte("2019-11-05").lte("2019-11-15").format("yyyy-MM-dd");
//      match 进行拆词，然后将拆开的词进行 or 匹配，匹配到一个就可以
//		boolBuilder.must(QueryBuilders.matchQuery("name", "孙"));
//      多个字段进行搜索， 第一个字段：搜索的内容，会进行拆词， 后面一次写入需要 匹配的字段
//      boolBuilder.must(QueryBuilders.multiMatchQuery("孙", "name"));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(2); // 获取记录数，默认10
//      第一个是获取字段，第二个是过滤的字段，默认获取全部
//		sourceBuilder.fetchSource(new String[] { "last_name", "age" }, new String[] {});
        sourceBuilder.fetchSource(new String[]{}, new String[]{});

        //索引，那个库
        SearchRequest searchRequest = new SearchRequest("sun");
        //类型type， 那个表
        searchRequest.types("sundc");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String data = JSONObject.toJSONString(sourceAsMap);
            System.out.println(data);
        }
        return searchHits;
    }


    /**
     * @Description TODO-SDC 多条件查询 and 关系
     * @Author  SunDC
     * @Date   2021/2/20 下午2:16
     * @Param
     * @Return
     * @Exception
     *
     */
    public static Object queryAndData() throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        /**
         * 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
         */
//      match_phrase 完全匹配，不进行拆词
        boolBuilder.must(QueryBuilders.matchPhraseQuery("name", "孙德超"));
//      数字可以按照String进行匹配
//      boolBuilder.must(QueryBuilders.matchPhraseQuery("age", "100"));
        boolBuilder.must(QueryBuilders.rangeQuery("age").gte(99).lte(101));
//      日期范围
//      QueryBuilders.rangeQuery("test2").gte("2019-11-05").lte("2019-11-15").format("yyyy-MM-dd");
//      match 进行拆词，然后将拆开的词进行 or 匹配，匹配到一个就可以
//		boolBuilder.must(QueryBuilders.matchQuery("name", "孙"));
//      多个字段进行搜索， 第一个字段：搜索的内容，会进行拆词， 后面一次写入需要 匹配的字段
//      boolBuilder.must(QueryBuilders.multiMatchQuery("孙", "name"));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(2); // 获取记录数，默认10
//      第一个是获取字段，第二个是过滤的字段，默认获取全部
//		sourceBuilder.fetchSource(new String[] { "last_name", "age" }, new String[] {});
        sourceBuilder.fetchSource(new String[]{}, new String[]{});

        //索引，那个库
        SearchRequest searchRequest = new SearchRequest("sun");
        //类型type， 那个表
        searchRequest.types("sundc");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String data = JSONObject.toJSONString(sourceAsMap);
            System.out.println(data);
        }
        return searchHits;
    }


    /**
     * @Description TODO-SDC 多条件查询 AND or 关系
     * @Author  SunDC
     * @Date   2021/2/20 下午2:16
     * @Param
     * @Return
     * @Exception
     *
     */
    public static Object queryAndORData() throws IOException {

        RestHighLevelClient client = InitClient.getClient();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        /**
         * 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
         */
//      match_phrase 完全匹配，不进行拆词
        boolBuilder.must(QueryBuilders.matchPhraseQuery("name", "孙德超"));
//      数字可以按照String进行匹配
//      boolBuilder.must(QueryBuilders.matchPhraseQuery("age", "100"));
        BoolQueryBuilder boolBuilderOr = QueryBuilders.boolQuery();
        boolBuilderOr.should(QueryBuilders.rangeQuery("age").gte(108).lte(222));
        boolBuilderOr.should(QueryBuilders.matchPhraseQuery("msg", "程序员"));
        boolBuilder.must(boolBuilderOr);
//      日期范围
//      QueryBuilders.rangeQuery("test2").gte("2019-11-05").lte("2019-11-15").format("yyyy-MM-dd");
//      match 进行拆词，然后将拆开的词进行 or 匹配，匹配到一个就可以
//		boolBuilder.must(QueryBuilders.matchQuery("name", "孙"));
//      多个字段进行搜索， 第一个字段：搜索的内容，会进行拆词， 后面一次写入需要 匹配的字段
//      boolBuilder.must(QueryBuilders.multiMatchQuery("孙", "name"));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(2); // 获取记录数，默认10
//      第一个是获取字段，第二个是过滤的字段，默认获取全部
//		sourceBuilder.fetchSource(new String[] { "last_name", "age" }, new String[] {});
        sourceBuilder.fetchSource(new String[]{}, new String[]{});

        //索引，那个库
        SearchRequest searchRequest = new SearchRequest("sun");
        //类型type， 那个表
        searchRequest.types("sundc");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String data = JSONObject.toJSONString(sourceAsMap);
            System.out.println(data);
        }
        return searchHits;
    }


    /**
     * @Description TODO-SDC 排序
     * @Author  SunDC
     * @Date   2021/2/20 下午2:16
     * @Param
     * @Return
     * @Exception
     *
     */
    public static Object queryOrder() throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        /**
         * 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
         */
       boolBuilder.must(QueryBuilders.multiMatchQuery("孙", "name"));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.sort("age", SortOrder.DESC);
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(10); // 获取记录数，默认10
        sourceBuilder.fetchSource(new String[]{}, new String[]{});

        //索引，那个库
        SearchRequest searchRequest = new SearchRequest("sun");

        //类型type， 那个表
        searchRequest.types("sundc");
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String data = JSONObject.toJSONString(sourceAsMap);
            System.out.println(data);
        }
        return searchHits;
    }


}
