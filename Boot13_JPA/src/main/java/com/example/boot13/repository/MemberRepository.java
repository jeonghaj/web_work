package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Member;

//extends JpaRepository<Entity type, Entity 의 id type>
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	//필요하다면 정해진 형식에 따라 메소드를 추가할 수 있다.
	
	/*
	 *	정렬된 결과를 select 하는 메소드를 custom 으로 추가하기 (정해진 형식이 있다)
	 *
	 *  findAllByOrderBy칼럼명Desc()
	 *  findAllByOrderBy칼럼명asc()
	 *  
	 *  칼럼명은 camel case 로 작성
	 */
	public List<Member> findAllByOrderByNumDesc();
}
