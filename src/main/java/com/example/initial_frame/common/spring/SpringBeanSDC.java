package com.example.initial_frame.common.spring;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class SpringBeanSDC {
    public String name ;
    public String msg ;

    @Bean(name = "springBean")
    public SpringBeanSDC springBean() {
        SpringBeanSDC springBean = new SpringBeanSDC();
        springBean.setMsg("测试-msg");
        springBean.setName("测试-name");
        return springBean;
    }

}
