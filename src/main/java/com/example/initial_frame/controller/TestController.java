package com.example.initial_frame.controller;

import com.example.initial_frame.bean.TestBean;
import com.example.initial_frame.common.exception.SDCException;
import com.example.initial_frame.common.restful.ResponseData;
import com.example.initial_frame.common.utils.ResultResponseUtil;
import com.example.initial_frame.common.validated.*;
import com.example.initial_frame.service.DBTestSeivice;
import org.apache.log4j.Logger;
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
    private static Logger logger= Logger.getLogger(TestController.class); // 获取logger实例
    @Autowired
    DBTestSeivice dbTestSeivice;


    @RequestMapping(value = "test", method = {RequestMethod.POST})
    public ResponseData test(@Validated(Insert.class) @RequestBody TestBean testBean, BindingResult result) {
        logger.info("测试SpringMVC 前后交互参数类型校验问题");
        ResultResponseUtil.check(result);
        return ResponseData.SUCCESS(null);
    }


    @RequestMapping(value = "exceptionTest", method = {RequestMethod.POST})
    public ResponseData exceptionTest() {
        logger.info("测试异常处理");
        throw new SDCException("自定义异常");
    }

    @RequestMapping(value = "dbTest", method = {RequestMethod.POST})
    public ResponseData dbTest(String msg) {
        dbTestSeivice.insertData(msg);
        return ResponseData.SUCCESS(null);
    }

}
