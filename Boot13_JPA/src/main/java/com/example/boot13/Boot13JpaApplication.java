package com.example.boot13;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpDeptDto;
import com.example.boot13.dto.EmpDto;
import com.example.boot13.dto.PhoneDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.entity.Phone;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;
import com.example.boot13.repository.PhoneRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@SpringBootApplication
public class Boot13JpaApplication {
	// JPA EntityManagerFactory 객체 주입 받기
	@Autowired
	EntityManagerFactory emf;
	
	//PhoneRepository 객체 주입 받기
	@Autowired
	PhoneRepository phoneRepo;
	@Autowired
	DeptRepository deptRepo;
	@Autowired
	EmpRepository empRepo;
	
	@PostConstruct
	public void init() {
		// 이 객체를(객체안에 있는 정보) 영속화(persistence)즉 영구 저장하고싶다
		Phone p1 = Phone.builder().name("아이폰15").company("apple").price(150).build();
		Phone p2 = Phone.builder().name("갤럭시S24").company("samsung").price(130).build();
		Phone p3= Phone.builder().name("아이폰16").company("apple").price(200).build();
		Phone p4 = Phone.builder().name("갤럭시S25").company("samsung").price(170).build();
		
		//EntityManager 객체 얻어내서
		EntityManager em = emf.createEntityManager();
		//하나의 트렌젝션을 시작한다.
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			//EntytyManager 객체의 메소드를 이용해서 저장한다.
			em.persist(p1);
			em.persist(p2);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		
		phoneRepo.save(p3);
		phoneRepo.save(p4);
		
		List<Phone> phoneList = phoneRepo.findAll();
		for(Phone tmp:phoneList) {
			System.out.println(tmp.getId()+"|"+tmp.getName()+"|"+tmp.getCompany());
		}
		
		List<PhoneDto> list = phoneRepo.findAll().stream().map(PhoneDto::toDto).toList();
		for(PhoneDto tmp:list) {
			//phoneDto 는 @Data 어노테이션이 있어서 객체를 직접 출력해도 구조를 확인할 수 있다.
			System.out.println(tmp);
		}
		//oracle DB 를 사용할때는 주석처리 // h2 DB 에만 사용 가능
		//아래 메소드를 호출
		//initEmpDept();
	}
	
	public void initEmpDept() {
		/*
		 *  emp, dept 셈플 데이터를 JPQL 을 이용해서 넣어주기 
		 */
		
		//EntityManager 객체 얻어내서 
		EntityManager em=emf.createEntityManager();
		//하나의 트랜젝션을 시작한다 
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		try {
			List<String> queries = new ArrayList<>();
			// JPQL
			queries.add("INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');");
			queries.add("INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');");
			queries.add("INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');");
			queries.add("INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7369,'SMITH','CLERK',7902,parsedatetime('17-12-1980','dd-MM-yyyy'),800,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7499,'ALLEN','SALESMAN',7698,parsedatetime('20-02-1981','dd-MM-yyyy'),1600,300,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7521,'WARD','SALESMAN',7698,parsedatetime('22-02-1981','dd-MM-yyyy'),1250,500,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7566,'JONES','MANAGER',7839,parsedatetime('02-04-1981','dd-MM-yyyy'),2975,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7654,'MARTIN','SALESMAN',7698,parsedatetime('28-09-1981','dd-MM-yyyy'),1250,1400,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7698,'BLAKE','MANAGER',7839,parsedatetime('01-05-1981','dd-MM-yyyy'),2850,NULL,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7782,'CLARK','MANAGER',7839,parsedatetime('09-06-1981','dd-MM-yyyy'),2450,NULL,10);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7788,'SCOTT','ANALYST',7566,parsedatetime('13-07-1987','dd-MM-yyyy'),3000,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7839,'KING','PRESIDENT',NULL,parsedatetime('17-11-1981','dd-MM-yyyy'),5000,NULL,10);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7844,'TURNER','SALESMAN',7698,parsedatetime('08-09-1981','dd-MM-yyyy'),1500,0,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7876,'ADAMS','CLERK',7788,parsedatetime('13-07-1987','dd-MM-yyyy'),1100,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7900,'JAMES','CLERK',7698,parsedatetime('03-12-1981','dd-MM-yyyy'),950,NULL,30);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7902,'FORD','ANALYST',7566,parsedatetime('03-12-1981','dd-MM-yyyy'),3000,NULL,20);");
			queries.add("INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7934,'MILLER','CLERK',7782,parsedatetime('23-01-1982','dd-MM-yyyy'),1300,NULL,10);");

			//반복문 돌면서 실행할 쿼리를 얻어내서 
			for (String query : queries) {
				//직접 실행한다 
			    em.createNativeQuery(query).executeUpdate();
			}

			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}	
		// DeptRepository 객체가 리턴해주는 List<Dept> 를 이용해서 List<DeptDto> 객체 얻어내기
		List<DeptDto> deptList = deptRepo.findAll().stream().map(DeptDto::toDto).toList();
		for(DeptDto tmp:deptList) {
			//DeptDto 는 @Data 어노테이션이 붙어있는 클래스임으로 바로 출력해도 해당객체안에 들어있는 정보를 볼 수 있다.
			System.out.println(tmp);
		}
		
		//Emp 관련 정보도 출력
		List<EmpDto> empList = empRepo.findAll().stream().map(EmpDto::toDto).toList();
		for(EmpDto tmp:empList) { 
			System.out.println(tmp);
		}
		
		List<EmpDeptDto> empDeptList = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		for(EmpDeptDto tmp:empDeptList) {
			System.out.println(tmp);
		}
		
		//10번 부서의 정보 얻어오기
		Dept d10 = deptRepo.findById(10).get();
		System.out.println("10번 부서에 근무하는 사원의 숫자 : "+d10.getList().size());
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Boot13JpaApplication.class, args);
	}

}
