package com.example.initial_frame.dao;

import com.example.initial_frame.bean.login.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources,Integer> {

    @Query(value="SELECT * from resources",nativeQuery=true)
    List<Resources> getAll();
}
