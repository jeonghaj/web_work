package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

}
