package com.example.boot13;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.boot13.dto.PhoneDto;
import com.example.boot13.entity.Phone;
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
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Boot13JpaApplication.class, args);
	}

}
