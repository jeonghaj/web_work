package com.example.boot13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpDeptDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;

@Controller
public class EmployController {
	// 테스트의 편의를 위해 서비스를 만들지 않고 바로 repository 객체 활용
	@Autowired private EmpRepository empRepo;
	@Autowired private DeptRepository deptRepo;
	
	@GetMapping("/emp/list")
	public String list(Model m) {
		//응답에 필요한 데이트를 얻어와서
//		List<EmpDto> list = empRepo.findAll().stream().map(EmpDto::toDto).toList();
		List<EmpDeptDto> list = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		
		//Model 객체에 담고
		m.addAttribute("list",list);
		//탬플릿 페이지에서 사원목록 응답
		return "emp/list";
	}
	
	@GetMapping("/emp/dept")
	public String dept(Model m, int deptno) {
		Dept dept = deptRepo.findById(deptno).get();
		
		DeptDto dto = DeptDto.toDto(dept);
		
		m.addAttribute("dto", dto );
		return "emp/dept";
	}
}
