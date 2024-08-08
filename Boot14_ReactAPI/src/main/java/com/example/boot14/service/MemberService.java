package com.example.boot14.service;

import java.util.List;

import com.example.boot14.dto.MemberDto;

public interface MemberService {
	public MemberDto addMember(MemberDto dto);
	public void updateMember(MemberDto dto);
	public void deleteMember(int num);
	public MemberDto selectOne(int num);
	public List<MemberDto> selectList();
}
