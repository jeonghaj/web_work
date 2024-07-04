package com.example.boot05.dao;

import java.util.List;

import com.example.boot05.dto.MemberDto;

public interface MemberDao {	
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num);
	public void update(MemberDto dto);
}
