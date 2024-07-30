package com.example.boot12.service;

import org.springframework.ui.Model;

import com.example.boot12.dto.UserDto;

public interface UserService {
	public void addUser (UserDto dto);
	public void getInfo (Model model);
	public void updateUser(UserDto dto);
	public void updatePassword(UserDto dto);
}
