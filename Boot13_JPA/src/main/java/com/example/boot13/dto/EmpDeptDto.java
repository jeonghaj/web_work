package com.example.boot13.dto;

import com.example.boot13.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpDeptDto {
	private int empno;
	private String ename;
	private int deptno;
	private String dname;
	private String job;
	private String loc;
	
	public static EmpDeptDto toDto(Emp emp) {
		
		return EmpDeptDto.builder()
				.empno(emp.getEmpno())
				.ename(emp.getEname())
				.deptno(emp.getDept().getDeptno())
				.dname(emp.getDept().getDname())
				.loc(emp.getDept().getLoc())
				.build();
	}
}
