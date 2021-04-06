package com.example.initial_frame.common.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class SpringTest1 {

    @Autowired
    SpringBeanSDC springBean;

    @Autowired
    SpringTest springTest;


    public void test(){
        System.out.println(System.identityHashCode(springBean));
    }


    @GetMapping("/test1")
    public  void test1() {
        test();
        System.out.println(springBean.getMsg());
        springBean.setMsg("修改过后");
        springTest.test();
    }


    @GetMapping("/test2")
    public  void test2() {
        test();
        System.out.println(springBean.getMsg());
        springTest.test();
    }
    @GetMapping("/test3")
    public  void test3() {
        test();
        System.out.println(springBean.getMsg());
        springTest.test0();
    }
}
