package com.oracle.oBootJpa02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;

@Controller
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	// Dispatcher Servlet
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start...");
	// View Resolver 
		return "members/createMemberForm";
	}
	// 저장은 postmapping!
	@PostMapping(value = "/members/save")
	public String create(Member member) {
		System.out.println("MemberController create start..");
		System.out.println("member.getName() -> " + member.getName());
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("memberList.get(0).getName()-> " + memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam().getName()-> " + memberList.get(0).getTeam().getName());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
	
	@PostMapping(value = "/members/search")
	public String search(Member member, Model model) {
		System.out.println("/members/search member.getName() -> " + member.getName());
		List<Member> memberList = memberService.getListSearchMember(member.getName());
		System.out.println("/members/search memberList.size() -> " + memberList.size());
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm")
	public String memberModify(Member member3, Model model) {
		
		System.out.println("MemberController memberModify id -> " + member3.getId());
		Optional<Member> member = memberService.findByMember(member3.getId());
		
		Member member2 = new Member();
		member2.setId(member.get().getId());
		member2.setName(member.get().getName());
		member2.setTeam(member.get().getTeam());
		System.out.println("memberModifyForm member -> " + member);
		model.addAttribute("member", member2);
		return "members/memberModify";
	}
	
	@GetMapping(value = "/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController memberUpdate member -> " + member);
		memberService.memberUpdate(member);
		return "redirect:/members";
	}
	
}
