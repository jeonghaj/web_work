package com.example.boot13.dto;

import com.example.boot13.entity.Cook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CookDto {
	private Long num;
	private String name;
	private String recipe;
	
	public static CookDto toDto(Cook entity) {
		return CookDto.builder()
				.num(entity.getNum())
				.name(entity.getName())
				.recipe(entity.getRecipe())
				.build();
	}
}
