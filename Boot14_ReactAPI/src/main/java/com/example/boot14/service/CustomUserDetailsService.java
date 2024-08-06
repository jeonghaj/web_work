package com.example.boot14.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.boot14.dto.UserDto;
import com.example.boot14.repository.UserDao;




// bean 으로 만들기 위한 어노테이션 
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired private UserDao dao;
	
	//Spring Security 가 로그인 처리시 호출하는 메소드 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//1. form 에 입력한 userName 을 이용해서 사용자의 자세한 정보를 얻어온다.
		UserDto dto=dao.getData(username);
		System.out.println(dto);
		//만일 저장된 userName 이 없다면 
		if(dto==null) {
			//예외를 발생시킨다
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다");
		}
		
		//있다면 해당정보를 이용해서 UserDetails 객체를 만들어서 리턴해 주어야 한다 

		//권한 목록을 List 에 담아서  (지금은 1개 이지만)
		List<GrantedAuthority> authList=new ArrayList<>();
		// Authority 는 접두어로 "ROLE_" 가 붙어 있어야 한다. 
		authList.add(new SimpleGrantedAuthority("ROLE_"+dto.getRole()));
		
		//UserDetails 객체를 생성해서 
		UserDetails ud=new User(dto.getUserName(), dto.getPassword(), authList);
		//리턴해준다.
		return ud;
	}

}

























