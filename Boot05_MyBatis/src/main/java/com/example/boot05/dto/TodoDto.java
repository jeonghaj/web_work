package com.example.boot05.dto;

public class TodoDto {
	private int num;
	private String todo;
	
	TodoDto(){}

	public TodoDto(int num, String todo) {
		super();
		this.num = num;
		this.todo = todo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

}
