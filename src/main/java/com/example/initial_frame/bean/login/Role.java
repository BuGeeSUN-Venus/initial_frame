package com.example.initial_frame.bean.login;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;
}