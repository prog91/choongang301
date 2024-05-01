package com.oracle.oBootHello.repository;

import java.util.List;

import com.oracle.oBootHello.domain.Member1;

public interface MemberRepository {
	Member1 		save(Member1 member1);
	List<Member1>   findAll();
}
