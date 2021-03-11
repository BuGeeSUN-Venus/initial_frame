package com.example.initial_frame.controller;

import com.example.initial_frame.common.restful.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTestController {

    @GetMapping("/hello")
    public ResponseData login(){
        return ResponseData.SUCCESS(null);
    }



    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }
    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }
    @GetMapping("/db/hello")
    public String db() {
        return "hello db";
    }


}
