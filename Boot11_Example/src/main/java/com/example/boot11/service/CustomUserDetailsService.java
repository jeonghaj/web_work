package com.example.boot11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.boot11.dto.UserDto;
import com.example.boot11.repository.UserDao;


//bean 으로 만들기 위한 어노테이션
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired private UserDao dao;
	
	//Spring Security 가 로그인 처리시 호출하는 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username : "+username);
		//username 에 해당되는 사용자 정보를 DB에서 읽어와서(Dao 필요)
		UserDto dto = dao.getData(username);
		//만일 DB에 없다면 Exception 을 발생시키고
		if(dto == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
		}
		//있다면 해당정보를 이용해서 UserDetails 객체를 만들어서 리턴해 주어야 한다.
		
		//권한 목록을 List 에 담아서 (지금은 1개지만)
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_"+dto.getRole()));
		
		//UserDetails 객체를 생성해서
		UserDetails ud = new User(dto.getUserName(), dto.getPassword(), authList);
		//리턴해준다.
		return ud;
	}
	
}
