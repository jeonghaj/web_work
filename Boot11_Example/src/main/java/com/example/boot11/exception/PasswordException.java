package com.example.boot11.exception;

public class PasswordException extends RuntimeException {
	//생성자
	public PasswordException(String message) {
		super(message); // 생성자에 전달되는 예외 메세지를 부모 생성자에 넘겨준다.
		//부모 생성자에 넘겨준 예외 메세지는 이 객체의 .getMessage() 메소드를 호출하면 리턴된다.
	}
}
