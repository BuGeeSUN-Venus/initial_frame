package com.example.initial_frame.bean.enticy;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="db_enticy_demo")
public class EnticyDemo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    private Integer id ;

    private String msg ;
}
