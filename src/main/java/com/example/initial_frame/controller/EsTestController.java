package com.example.initial_frame.controller;


import com.example.initial_frame.common.restful.ResponseData;
import com.example.initial_frame.service.EsTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 *  @author: SunDC EsDemo
 *  @Date: 2021/1/8 下午4:19
 *  @Description:
 */
@RestController
@RequestMapping("/test/es")
public class EsTestController {


    @Autowired
    EsTestService esTestService ;

    @RequestMapping(value = "checkIndexExist", method = {RequestMethod.GET})
    public ResponseData dbTest(String index) throws IOException {
        esTestService.checkIndexExist(index);
        return ResponseData.SUCCESS(null);
    }


    @RequestMapping(value = "master", method = {RequestMethod.POST})
    public ResponseData master(@RequestBody Map index) throws IOException {
        esTestService.master(index);
        return ResponseData.SUCCESS(null);
    }

}
