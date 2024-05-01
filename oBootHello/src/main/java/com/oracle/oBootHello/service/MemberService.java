package com.oracle.oBootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.repository.MemberRepository;
import com.oracle.oBootHello.repository.MemoryMemberRepository;

@Service
public class MemberService {
	// 전통적
	//	MemberRepository memberRepository = new MemoryMemberRepository();
	
	private final MemberRepository memberRepository;
	// 오토와이어드 - 빈을 연동관계로 맺어놓는다(어노테이션으로 연동을 맺는 방식)
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	// 회원 가입
	public Long memberSave (Member1 member1) {
		System.out.println("MemberService memberSave start...");
		memberRepository.save(member1);
		return member1.getId();
	}
	
	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("memList.size() -> " + memList.size());
		return memList;
	}
	
	
}
