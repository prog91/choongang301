package com.oracle.oBootBoard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootBoard.command.BCommand;
import com.oracle.oBootBoard.command.BExecuteCommand;
import com.oracle.oBootBoard.command.BListCommand;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	// logger 설정(sysout이 보기 좋긴 함)
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	
	private final BExecuteCommand bExecuteService;
	@Autowired
	public BController(BExecuteCommand bExecuteService) {
		this.bExecuteService = bExecuteService;
	}
	
	@RequestMapping("list")
	public String list(Model model) {
		logger.info("list start...");
		bExecuteService.bListCmd(model); 
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		logger.info("write_view start...");
		
		return "write_view";
	}
	
	@PostMapping(value = "/write") // 저장은 무조건 post방식으로 만드는 게 좋음
	public String write(HttpServletRequest request, Model model) {
		logger.info("write start...");
		
		model.addAttribute("request", request);
		bExecuteService.bWriteCmd(model);
		
		return "redirect:list";
	}
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		bExecuteService.bContentCmd(model);
		return "content_view";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		logger.info("modify start...");
		model.addAttribute("request", request);
		bExecuteService.bModifyCmd(model);
		
		return "redirect:list"; 
	}
	//감사함둥
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view start...");
		
		model.addAttribute("request", request);
		bExecuteService.bReplyViewCmd(model);
		return "reply_view";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		bExecuteService.bReplyCmd(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		bExecuteService.bDeleteCmd(model);
		
		return "redirect:list";
	}
	
	
}
