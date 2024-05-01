package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	// 전통적 방식
	// MemberService memberService = new MemberService();
	// BOOT 방식 (멤버서비스 인스턴스를 파이널로 지정 후 this.멤버서비스로 받아옴)
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService =memberService;
	}
	
	//핸들러매핑을 통해 컨트롤러를 찾아간다
	@GetMapping(value = "members/memberForm")
	public String memberForm() {
		System.out.println("MemberController/members/memberForm Start...");
		// D/S --> V/R(여기서 prefix suffix 받아서 실행) --> templates/ + members/memberForm + .html
		return "members/memberForm";
	}
	// 저장할 때는 post매핑을 쓴다 
	@PostMapping(value="/members/save")
	public String save(Member1 member1) {
		System.out.println("MemberController/members/save Start...");
		System.out.println("MemberController/members/save member1.getName()-> " + member1.getName());
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController/members/save id -> " + id);
		return "redirect:/";
	}
	@GetMapping(value="/members/memberList")
	public String memberList(Model model) {
		log.info("memberList start...");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		log.info("memberLists.size() -> {}",memberLists.size());
		
		return "members/memberList";
	}
}
