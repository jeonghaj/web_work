package com.example.boot11.exception;

public class NotOwnerException extends RuntimeException{
	
	public NotOwnerException(String message) {
		super(message);
	}
}
