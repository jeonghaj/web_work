package com.example.boot11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot11.dto.UserDto;
import com.example.boot11.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	@Autowired PasswordEncoder encoder;
	
	@Override
	public void addUser(UserDto dto) {
		//role 은 일반 USER 로 넣어준다.
		dto.setRole("USER");
		//비밀번호 암호화 해서 dto 에 다시 넣기
		String encodedPwd = encoder.encode(dto.getPassword());
		dto.setPassword(encodedPwd);
		//dao 객체를 이용해서 DB에 저장하기
		dao.insert(dto);
	}

	@Override
	public void getInfo(Model model) {
		//Spring Security 의 기능을 이용해서 이미 로그인한 계정의 userName 을 얻어온다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//로그인된 usrName 을 이용해서 UserDto 객체를 dao 를 통해서 얻어낸다.
		UserDto dto = dao.getData(userName);
		//Model 객체에 담아주기
		model.addAttribute("dto",dto);
	}

	@Override
	public void updatedUser(UserDto dto) {
		
	}

	@Override
	public void updatedPassword(UserDto dto) {
		//1. 로그인 된 userName 을 얻어낸다.
		
		//2. 기존의 비밀번호를 DB에 읽어와서
		
		//3. 입력한(구 비밀번호) 와 일치하는지 비교해서
		
		//4. 만일 일치하지 않으면 Exception 을 발생 시킨다. (throws)
		
		//5. 일치하면 새 비밀번호를 암호화해서 dto 에 담은 다음
		
		//6. userName 도 dto 에 담고 
		
		//7. DB에 비밀번호 수정반영을 한다.

	}

}
