package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

// @Controller + @ResponseBody = @RestController
@RestController
@RequiredArgsConstructor
public class EmpRestController {

	private final EmpService es;

	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController Start...");
		String hello = "안녕";
		// HttpMessageConverter --> StringConverter
		return hello;
	}
	
	// http://jsonviewer.stack.hu/
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(Dept dept) {
		System.out.println("@RestController dept.getDeptno() -> " + dept.getDeptno());
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(dept.getDeptno());
		// HttpMessageConverter(객체일 경우) --> JsonConverter
		return vo;
	}
	
	@RequestMapping("/sendVO3")
	 public List<Dept> sendVO3() {
		 System.out.println("@RestController sendVO3 Start..");
		 List<Dept> deptList = es.deptSelect();
		 return deptList;
	 }
	
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp) {
		System.out.println("@RestController empnoDelete Start..");
		System.out.println("@RestController empnoDelete emp.getEname() -> " + emp.getEname());
		int	 delStatus = es.deleteEmp(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		return delStatusStr;
	}
	
	
	
	
	
	
}
