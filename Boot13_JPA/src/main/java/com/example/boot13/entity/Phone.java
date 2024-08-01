package com.example.boot13.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
	// null 값이 가능한 Entity 필드는 반드시 참조 data type 이어야 한다ㄴ
	@Column(nullable = true)
	private Integer price;
	@Column(nullable = false)
	private Date regdate;
	
	//Entity 를 영속화 하기 직전에 뭔가 작업할게 있으면 @Prepersist 어노테이션을 활용
	@PrePersist
	public void onPersist() {
		//오라클에서 데이터를 넣을댸 SYSDATE 함수를 이용해서 넣는 효과를 낸다.
		regdate = new Date();
	}
	
}
