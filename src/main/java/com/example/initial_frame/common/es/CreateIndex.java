package com.example.initial_frame.common.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;


import java.io.IOException;

/**
 *  @author: SunDC ES索引生成，一般可以直接添加，会自动进行创建索引。  但是根据业务不同，建立索引会有类型，分词器设置，后续查询会起到关键作用
 *                 就是DB的建表语句
 *  @Date: 2021/1/19 上午11:06
 *  @Description:
 */
@Slf4j
public class CreateIndex {

    /**
    * @Description TODO-SDC 索引创建分配器
    * @Author  SunDC
    * @Date   2021/1/18 下午5:02
    * @Param
    * @Return
    * @Exception
    *
    */
    public static void  allot(String indexName ,Boolean isDel) throws IOException {
        switch(indexName){
            case "sun" :
                createSunIndex(indexName,isDel);
                break;
            case "" :
                break;
            default :
                createIndex(indexName,isDel);
        }



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
    public static void createIndex(String indexName, Boolean isDel) throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        indexName = indexName.toLowerCase();
        if(ESUtils.checkIndexExist(indexName)) {
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
                    .put("index.number_of_replicas", 0);
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
     * @Description TODO-SDC 创建索引 sun
     * @Author  SunDC
     * @Date   2021/1/8 下午5:02
     * @Param isDel 索引重复是否删除重建
     * @Return
     * @Exception
     *
     */
    public static void createSunIndex(String indexName, Boolean isDel) throws IOException {
        RestHighLevelClient client = InitClient.getClient();
        indexName = indexName.toLowerCase();
        if(ESUtils.checkIndexExist(indexName)) {
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
                    .put("index.number_of_replicas", 0);
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

}
