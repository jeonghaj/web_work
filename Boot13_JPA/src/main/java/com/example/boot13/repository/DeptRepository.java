package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{

}
