package com.example.initial_frame.service;

import com.example.initial_frame.bean.EnticyDemo;
import com.example.initial_frame.dao.EnticyDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBTestSeivice {

    @Autowired
    EnticyDemoRepository enticyDemoRepository;

    public void insertData(String msg) {
        EnticyDemo enticyDemo = new EnticyDemo();
        enticyDemo.setMsg(msg);
        enticyDemoRepository.save(enticyDemo);
    }
}
