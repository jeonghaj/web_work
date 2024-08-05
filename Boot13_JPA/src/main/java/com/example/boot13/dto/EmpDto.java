package com.example.boot13.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.boot13.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmpDto {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private double sal;
	private double comm;
	private Integer Deptno;
	
	public static EmpDto toDto(Emp emp) {
		// Data type 을 이용해서 원하는 문자열 형식을 얻어내기 위한 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//객체를 이용해서 원하는 문자열을 얻어낸다
		String hireDate = sdf.format(emp.getHiredate());
		
//		double comm = 0;
//		if(emp.getComm() != null) {
//			comm = emp.getComm();
//		}
		
		return EmpDto.builder()
				.empno(emp.getEmpno())
				.ename(emp.getEname())
				.job(emp.getJob())
				.mgr(emp.getMgr()==null ? 0 : emp.getMgr())
				.hiredate(hireDate)
				.sal(emp.getSal())
				.comm(emp.getComm()==null ? 0 : emp.getComm())
				.Deptno(emp.getDept().getDeptno())
				.build();
	}
}
