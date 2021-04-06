package com.example.initial_frame.common.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test_Async_Main {

    @Autowired
    Test_Async test_async ;

    @GetMapping("/async")
    public void async() throws InterruptedException {
        while(true){
            test_async.get();
            test_async.getA();
        }

    }

}
