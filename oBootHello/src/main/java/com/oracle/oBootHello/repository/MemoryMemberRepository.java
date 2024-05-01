package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

//@Repository // (컴포넌트로 등록시킴)
public class MemoryMemberRepository implements MemberRepository {

	private static  Map<Long, Member1> store = new HashMap<Long, Member1>();
	private static Long sequence = 0L;
	
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		System.out.println("MemoryMemberRepository sequence -> " + sequence);
		System.out.println("MemoryMemberRepository member1.getName() -> " + member1.getName());
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		// store의 value(Member1)
		List<Member1> listMember = new ArrayList<>(store.values());
		// size한번 확인하고
		System.out.println("MemoryMemberRepository findAll slistMember.size() -> " + listMember.size());
		// 리스트멤버 객체 리턴받기
		return listMember;
	}

}
