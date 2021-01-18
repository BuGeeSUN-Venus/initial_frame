package com.example.initial_frame.common.es;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 *  @author: SunDC Es工具类
 *  @Date: 2021/1/8 下午4:19
 *  @Description:
 */
@Slf4j
public class ESUtils {

    /**
    * @Description TODO-SDC 判断索引是否存在
    * @Author  SunDC
    * @Date   2021/1/8 下午5:02
    * @Param
    * @Return
    * @Exception
    *
    */
    public static boolean checkIndexExist(String index) throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        GetIndexRequest request = new GetIndexRequest(index);
        request.indices();
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        client.close();
        return exists;
    }

    /**
     * @Description TODO-SDC 创建索引 （类似分词器，数据格式等信息 自定义强，不可以一概而论 故一般采取后期按需修改）（一般使用下，不用自己手动创建索引，插入数据默认生成）
     * @Author  SunDC
     * @Date   2021/1/8 下午5:02
     * @Param isDel 索引重复是否删除重建
     * @Return
     * @Exception
     *
     */
    public static void createIndex(String indexName ,Boolean isDel) throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        indexName = indexName.toLowerCase();
        if(checkIndexExist(indexName)) {
            //删除重建
            if (!isDel) {
                log.info("索引——>{}  已经存在",indexName);
               return;
            }
            client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
        }
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            //设置， shards 切片为 5 ，设置副本为 1
            Settings.Builder put = Settings.builder().put("index.number_of_shards", 5)
                    .put("index.number_of_replicas", 1);
            createIndexRequest.settings(put);

            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            if (acknowledged) {
                log.info("索引——>{}  创建成功",indexName);
            } else {
                log.info("索引——>{}  创建失败",indexName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    /**
     * @Description TODO-SDC 创建索引
     * @Author  SunDC
     * @Date   2021/1/8 下午5:02
     * @Param isDel 索引重复是否删除重建
     * @Return
     * @Exception
     *
     */
    public static void delIndex(String indexName ) throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        if(checkIndexExist(indexName)) {
            client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
            log.info("索引——>{}  删除成功",indexName);
        }else{
            log.info("索引——>{}  不存在",indexName);
        }
        client.close();
    }


    /**
    * @Description TODO-SDC 插入数据
    * @Author  SunDC
    * @Date   2021/1/18 下午2:50
    * @Param
    * @Return
    * @Exception
    *
    */
    public static void insert(String index, String type, String id, Map<String, Object> param) throws Exception {
        RestHighLevelClient client = InitClient.getClient();
        IndexRequest indexRequest = new IndexRequest(index,type,id);
        indexRequest.source(param);
        client.index(indexRequest, RequestOptions.DEFAULT);
        client.close();
    }
    /**
     * @Description TODO-SDC 修改数据
     * @Author  SunDC
     * @Date   2021/1/18 下午2:50
     * @Param
     * @Return
     * @Exception
     *
     */
    public static void update(String index, String type, String id, Map<String, Object> param) throws Exception {
        RestHighLevelClient client = InitClient.getClient();
        UpdateRequest updateRequest = new UpdateRequest(index,type,id);
        updateRequest.doc(param);
        client.update(updateRequest, RequestOptions.DEFAULT);
        client.close();
    }
    /**
     * @Description TODO-SDC 根据id更新指定字段
     * @Author  SunDC
     * @Date   2021/1/18 下午2:50
     * @Param
     * @Return
     * @Exception
     *
     */
    public void updateEsById(String index, String type, String id, Map<String, Object> param) throws Exception {
        RestHighLevelClient client = InitClient.getClient();
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
        Set<Map.Entry<String, Object>> entries = param.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            xContentBuilder.field(entry.getKey(), entry.getValue());
        }
        xContentBuilder.endObject();
        updateRequest.doc(xContentBuilder);
        client.update(updateRequest, RequestOptions.DEFAULT);
        client.close();
    }
    /**
     * @Description TODO-SDC 根据id查询文档内容
     * @Author  SunDC
     * @Date   2021/1/18 下午2:50
     * @Param
     * @Return
     * @Exception
     *
     */
    public static JSONObject getEsDataById(String index, String type, String id) throws Exception{
        RestHighLevelClient client = InitClient.getClient();
        //拼接查询内容
        String body = index + "/" + type + "/" + id;
        Request request = new Request("GET", body);
        Response response = client.getLowLevelClient().performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject json = JSONObject.parseObject(responseBody);
        client.close();
        return json;
    }
}
