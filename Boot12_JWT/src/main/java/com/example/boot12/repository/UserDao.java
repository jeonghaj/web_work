package com.example.boot12.repository;

import com.example.boot12.dto.UserDto;

public interface UserDao {
	public UserDto getData(String userName);
	public void insert(UserDto dto);
	public void updatePwd(UserDto dto);
	public void update(UserDto dto);
	
}
