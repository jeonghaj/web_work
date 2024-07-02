package com.example.helloboot.auto;

import org.springframework.stereotype.Component;

@Component
public class MyCar implements Car{

	@Override
	public void drive() {
		System.out.println("내차가 열심히 달려요!");
	}

}
