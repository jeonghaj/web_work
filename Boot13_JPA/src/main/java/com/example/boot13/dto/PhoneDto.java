package com.example.boot13.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.boot13.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhoneDto {
	private long id;
	private String name;
	private String company;
	private int price;
	private String regdate;
	
	public static PhoneDto toDto(Phone phone) {
		// Data type 을 이용해서 원하는 문자열 형식을 얻어내기 위한 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//객체를 이용해서 원하는 문자열을 얻어낸다
		// 2014.08.01 목 오후 6:10:30 형식의 문자열
		String result = sdf.format(phone.getRegdate());
		
		return PhoneDto.builder()
				.id(phone.getId())
				.name(phone.getName())
				.company(phone.getCompany())
				.price(phone.getPrice())
//				.regdate(phone.getRegdate().toString())
				.regdate(result)
				.build();
	}
}
