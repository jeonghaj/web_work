package com.example.boot05.dto;

import org.apache.ibatis.type.Alias;

// MemberDto 클래스에 별칭을 부여해서 mapper xml 문서에서 별칭을 이용해서 type을 사용할 수 있도록 한다.
// parameterType="memberDto" or resultType="memberDto"
@Alias("memberDto")
public class MemberDto {
	private int num;
	private String name;
	private String addr;
	
	public MemberDto() {}

	public MemberDto(int num, String name, String addr) {
		super();
		this.num = num;
		this.name = name;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
