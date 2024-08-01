package com.example.boot13.entity;

import com.example.boot13.dto.CookDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter // Entity 는 Getter 만 제공한다. (한번 만들어진 객체는 readOnly 로 사용되도록)
@Setter
@Entity(name = "Cook_info")
public class Cook {
	@Id // primary key (대표키) 로 설정하겠다
	@GeneratedValue(strategy = GenerationType.AUTO) // 번호는 자동으로 들어가게 한다(시퀀스 사용)
	private Long num;
	private String name;
	private String recipe;
	
	//dto 를 entity 로 변환하는 static 메소드
	public static Cook toEntity(CookDto dto) {
		return Cook.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.recipe(dto.getRecipe())
				.build();
	}
}
