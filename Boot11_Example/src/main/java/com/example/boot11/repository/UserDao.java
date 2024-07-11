package com.example.boot11.repository;

import com.example.boot11.dto.UserDto;

public interface UserDao {
	public UserDto getData(String userName);
	public void insert(UserDto dto);
	public void updatePwd(UserDto dto);
	
}
