package com.example.initial_frame.controller;

import com.example.initial_frame.bean.TestBean;
import com.example.initial_frame.common.exception.SDCException;
import com.example.initial_frame.common.restful.ResponseData;
import com.example.initial_frame.common.utils.ResultResponseUtil;
import com.example.initial_frame.common.validated.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping(value = "test", method = {RequestMethod.POST})
    public ResponseData test(@Validated(Insert.class) @RequestBody TestBean testBean, BindingResult result) {
        log.info("测试SpringMVC 前后交互参数类型校验问题");
        ResultResponseUtil.check(result);
        return ResponseData.SUCCESS(null);
    }


    @RequestMapping(value = "exceptionTest", method = {RequestMethod.POST})
    public ResponseData exceptionTest() {
        log.info("测试异常除理");
        throw new SDCException("自定义异常");
    }


}
