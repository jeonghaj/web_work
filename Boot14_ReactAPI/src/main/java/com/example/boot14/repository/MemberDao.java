package com.example.boot14.repository;

import java.util.List;

import com.example.boot14.dto.MemberDto;

public interface MemberDao {
	public void insert(MemberDto dto);
	public void update(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num);
	public List<MemberDto> getList();
	public int getSequence();
}
