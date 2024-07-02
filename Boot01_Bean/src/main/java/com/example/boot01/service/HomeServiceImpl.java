package com.example.boot01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 *  spring 이 관리하는 객체로 만드는 방법
 *  
 *  1. component scan 이 일어나는 위치에 클래스가 존재해야한다.
 *  2. 적절한 어노테이션이 클래스에 붙어 있어야한다.
 */
@Component 
public class HomeServiceImpl implements HomeService{
	/*
	 *  필요한 type 을 필드로 선언하고 @Autowired 어노테이션을 붙여 놓으면 해당 객체가 자동 주입된다.
	 */
	@Autowired 
	private Drill drill;
	
	@Override
	public void clean(String name) {
		System.out.println(name+" 의 집을 청소해요!");
	}

	@Override
	public void wash(String name) {
		System.out.println(name+" 의 빨래를 빨아요~");
	}

	@Override
	public void hole(String name) {
		System.out.println(name+" 에 구멍을 뚤어요!");
		drill.hole();
	}

}










