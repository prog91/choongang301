package com.oracle.oBootJpaApi01.repository;

import java.util.List;

import com.oracle.oBootJpaApi01.domain.Member;

import jakarta.validation.Valid;

public interface MemberRepository {
	List<Member> findAll();

	Long save(@Valid Member member);

	void updateByMember(Member member);

	Member findByMember(Long memberId);

}