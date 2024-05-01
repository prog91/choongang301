package com.oracle.oBootJpa02;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;
import com.oracle.oBootJpa02.service.MemberService;
//@SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
//반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고
//테스트가 끝나면 트랜잭션을 강제로 롤백
@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@BeforeEach
	public void before1() {
		System.out.println("Test @BeforeEach ...");
	}
	
	public void memberSave() {
		// 1. 조건
		Member member = new Member();
		member.setTeamname("고구려");
		member.setName("강이식");
		
		// 2. 행위
		Member member3 = memberService.join(member);
		
		// 3. 결과
		System.out.println("MemberServiceTest memberSave member.getId() -> " + member.getId());
		System.out.println("MemberServiceTest memberSave member3.getId() -> " + member3.getId());
	}
	
}
