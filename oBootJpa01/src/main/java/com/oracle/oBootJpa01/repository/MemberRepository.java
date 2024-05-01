package com.oracle.oBootJpa01.repository;

import java.util.List;

import com.oracle.oBootJpa01.domain.Member;

public interface MemberRepository {
	Member          memberSave (Member member);
	List<Member>    findAllMember();
	List<Member> findByNames(String searchName);

}
