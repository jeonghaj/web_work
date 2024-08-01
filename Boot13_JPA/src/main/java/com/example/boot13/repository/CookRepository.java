package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Cook;
import com.example.boot13.entity.Member;

public interface CookRepository extends JpaRepository<Cook, Long>{

}
