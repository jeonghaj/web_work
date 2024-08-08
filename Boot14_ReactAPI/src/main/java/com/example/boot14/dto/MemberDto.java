package com.example.boot14.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("memberDto") // myBatis mapper xml 에서 사용할 type 별칭
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
