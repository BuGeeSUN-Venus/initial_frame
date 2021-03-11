package com.example.initial_frame.controller;

import com.example.initial_frame.bean.test.TestBean;
import com.example.initial_frame.common.exception.SDCException;
import com.example.initial_frame.common.restful.ResponseData;
import com.example.initial_frame.common.utils.ResultResponseUtil;
import com.example.initial_frame.common.validated.*;
import com.example.initial_frame.service.DBTestSeivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    DBTestSeivice dbTestSeivice;

    @RequestMapping(value = "test", method = {RequestMethod.POST})
    public ResponseData test(@Validated(Insert.class) @RequestBody TestBean testBean, BindingResult result) {
        ResultResponseUtil.check(result);
        return ResponseData.SUCCESS(null);
    }


    @RequestMapping(value = "exceptionTest", method = {RequestMethod.GET})
    public ResponseData exceptionTest() {
        throw new SDCException("自定义异常");
    }

    @RequestMapping(value = "dbTest", method = {RequestMethod.POST})
    public ResponseData dbTest(String msg) {
        dbTestSeivice.insertData(msg);
        return ResponseData.SUCCESS(null);
    }

    //获取环境属性
    public static void main(String[] args) {
        System.getProperty("java.library.path");
    }
}
