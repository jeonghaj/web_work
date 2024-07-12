package com.example.boot11.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired private SqlSession session;
	
	@Override
	public UserDto getData(String userName) {
		/*
		 * mapper's namespace : user
		 * sql's id : getData
		 * parameter type : String
		 * resultType : UserDto
		 */
		return session.selectOne("user.getData",userName);
	}

	@Override
	public void insert(UserDto dto) {
		/*
		 * mapper's namespace : user
		 * sql's id : insert
		 * parameter type : UserDto
		 * resultType : none
		 */
		session.insert("user.insert",dto);
	}

	@Override
	public void updatePwd(UserDto dto) {
		/*
		 * mapper's namespace : user
		 * sql's id : updatePwd
		 * parameter type : UserDto
		 * resultType : none
		 */
		session.update("user.updatePwd",dto);
	}

	@Override
	public void update(UserDto dto) {
		/*
		 * mapper's namespace : user
		 * sql's id : update
		 * parameter type : UserDto
		 * resultType : none
		 */
		session.update("user.update",dto);
	}

}
