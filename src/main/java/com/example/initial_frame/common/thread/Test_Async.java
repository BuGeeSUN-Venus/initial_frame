package com.example.initial_frame.common.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Test_Async {


    @Async
    public String get() throws InterruptedException {
        System.out.println("我是线程————————————————————："+Thread.currentThread().getName());
        Thread .sleep(100);
        return "data";
    }



    public String getA() throws InterruptedException {
        System.out.println("我是固定线程————————————————————："+Thread.currentThread().getName());
        Thread .sleep(100);
        return "data";
    }
}
