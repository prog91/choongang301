package com.oracle.oBootJpa01.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa01.domain.Member;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	// *** JPA DML 작업할 때 EntityManager(테이블매니저라고 보면 됨)는 필수다!! ***
	private final EntityManager em;
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public Member memberSave(Member member) {
		// 저장 method = persist
		em.persist(member);
		System.out.println("JpaMemberRepository memberSave member After..");
		return member;
	}

	@Override
	public List<Member> findAllMember() {
												  //JPA문법 
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
									.getResultList()
									;
		System.out.println("JpaMemberRepository findAllMember memberList.size()->" + memberList.size());
		return memberList;
	}


	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname->" + pname);
		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
									.setParameter("name", pname).getResultList();
		System.out.println("JpaMemberRepository memberList.size()->" + memberList.size());
		return memberList;
	}

}
