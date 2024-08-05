package com.example.boot13.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// DeptRepository 만들때
// extends JpaRepository<Dept, Integer> 선언하는것 만으로도 Bean 객체가 되고
public class Dept {
	@Id
	private Integer deptno;
	private String dname;
	private String loc;
	
	// 해당 부서에 속해있는 사원의 목록
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
	private List<Emp> list = new ArrayList<Emp>();
}
