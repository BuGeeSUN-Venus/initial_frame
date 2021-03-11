package com.example.initial_frame.bean.login;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="resources")
public class Resources {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    private Integer id;

    private String pattern;
    @Transient
    private List<Role> roles;

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
