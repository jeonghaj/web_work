package com.example.boot13.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// dto 로 만든다 // 테스트를위해 임시로 사용 원래는 dto로 만들면 안된다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name="PHONE_INFO") // 테이블명
public class Phone {
	// Id 라는 칼럼은 primary key 값으로 설정되도록 하겠다
	@Id
	// sequence 를 자동으로 생성
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; // 필드명이 칼럼명이 된다.
	private String company;
	private String name;
	private int price;
	
}
