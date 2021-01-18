package com.example.initial_frame.common.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 *  @author: SunDC ES 连接获取 记得释放client.close();
 *  极简版工厂模式 命名未加 factory
 *  正常使用必用ES连接池，先用简化版测试环境是否OK
 *  @Date: 2021/1/8 下午3:57
 *  @Description:
 */
public class InitClient {
    public static RestHighLevelClient getClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                //支持多个
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return client;
    }
}
