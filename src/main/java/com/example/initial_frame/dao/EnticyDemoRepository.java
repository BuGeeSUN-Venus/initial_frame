package com.example.initial_frame.dao;

import com.example.initial_frame.bean.enticy.EnticyDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnticyDemoRepository extends JpaRepository<EnticyDemo,Integer> {
}
