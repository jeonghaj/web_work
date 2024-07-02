package com.example.boot01.service;

import org.springframework.stereotype.Component;

// spring 이 component scan 을 해서 해당 클래스로 객체를 생성해서 관리하도록 어노테이션을 붙여 놓는다.
@Component
public class DrillImpl implements Drill{

	@Override
	public void hole() {
		System.out.println("윙~~~");
	}

}
