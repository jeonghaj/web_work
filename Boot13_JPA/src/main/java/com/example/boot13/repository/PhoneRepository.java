package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Phone;
// - JpaRepository 인터페이스를 상속받는 인터페이스를 정의한다.
// - 정의하는것 만으로 구현클래스가 자동으로 만들어지고 객체가 생성된다.
// - 만들어진 객체는 자동으로 spring 이 관리하는 bean 이 된다.
// extends JpaRepository <Entity 클래스, Entity 클래스 안에서 id 역할을 하는 칼럼의 data type>
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
