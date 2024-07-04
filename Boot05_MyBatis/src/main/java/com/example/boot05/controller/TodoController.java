package com.example.boot05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.example.boot05.dao.TodoDao;
import com.example.boot05.dto.TodoDto;

@Controller
public class TodoController {
	@Autowired
	private TodoDao dao;
	
	@GetMapping("/todo/list")
	public String list(Model model){
		List<TodoDto> list = dao.getList();
		model.addAttribute("list",list);
		
		return "todo/list";
	}

}
