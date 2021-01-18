package com.example.initial_frame.service;

import com.alibaba.fastjson.JSONObject;
import com.example.initial_frame.common.es.ESUtils;
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
//        ESUtils.createIndex("sun001",false);
//        Map map = new HashMap();
//        map.put("name","孙德超ipdate");
//        ESUtils.insert("sun001","sundc","001",map );
//          ESUtils.update("sun001","sundc","001",map );



        JSONObject esDataById = ESUtils.getEsDataById("sun001", "sundc", "001");
        System.out.println(esDataById);

    }
}
