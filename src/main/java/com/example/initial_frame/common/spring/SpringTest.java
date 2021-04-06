package com.example.initial_frame.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class SpringTest implements BeanFactoryAware{

    @Autowired
    SpringBeanSDC springBean;

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringTest.beanFactory =beanFactory;
    }
    public void test(){
        System.out.println(springBean.getMsg());
    }

    public void test0(){
        SpringBeanSDC ss = (SpringBeanSDC) beanFactory.getBean("springBean");
        System.out.println(ss.getMsg());
    }
}
