package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpDao     ed; 
	private final DeptDao    dd;
	private final Member1Dao md;
	
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl Start total..." );
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt->" + totEmpCnt);
		return totEmpCnt;
	}


	@Override
	public List<Emp> listEmp(Emp emp) {
		 List<Emp> empList = null;
		 System.out.println("EmpServiceImpl listManager Start..." );
		 empList = ed.listEmp(emp);
		 System.out.println("EmpServiceImpl listEmp empList.size()->" +empList.size());
		 return empList;
	}


	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpServiceImpl detail ...");
		Emp emp = null;
		emp = ed.detailEmp(empno);
		return emp;
	}


	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpServiceImpl update ...");
		int updateCount = 0;
		updateCount = ed.updateEmp(emp);
		return updateCount;
	}


	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listManager Start..." );
		empList =  ed.listManager();  
		System.out.println("EmpServiceImpl listEmp empList.size()->" +empList.size());
		return empList;
	}


	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect Start..." );
		deptList =  dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->" +deptList.size());
		return deptList;
	}


	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		System.out.println("EmpServiceImpl insert Start..." );
		result = ed.insertEmp(emp);
		return result;
	}


	@Override
	public List<Emp> listSearchEmp(Emp emp) {
		List<Emp> empSearchList = null;
		System.out.println("EmpServiceImpl listEmp Start..." );
		empSearchList = ed.empSearchList3(emp);
		System.out.println("EmpServiceImpl listSearchEmp empSearchList.size()->" +
		                    empSearchList.size());
		return empSearchList;	
	}


	@Override
	public int condTotalEmp(Emp emp) {
		System.out.println("EmpServiceImpl Start total..." );
		int totEmpCnt = ed.condTotalEmp(emp);
		System.out.println("EmpServiceImpl totalEmp totEmpCnt->" + totEmpCnt);
		return totEmpCnt;
	}


	@Override
	public int deleteEmp(int empno) {
		int result = 0;
		System.out.println("EmpServiceImpl delete Start..." );
		result =  ed.deleteEmp(empno);
		return result;
	}


	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDeptList = null;
		System.out.println("EmpServiceImpl listEmpDept Start..." );
		empDeptList =  ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept empDeptList.size()->" +
		                    empDeptList.size());
		return empDeptList;
	}


	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept Start...");
	    dd.insertDept(deptVO);
	}


	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept Start...");		
		dd.selListDept(map);
		
	}


	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id ->"+id);
		return md.memCount(id); 
	}


	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem Start..");
		return md.listMem(member1);	
	}


	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl deptName Start...");
		return ed.deptName(deptno);
	}


	@Override
	public int transactionInsertUpdate() {
		System.out.println("EmpServiceImpl transactionInsertUpdate Start..");
		// return md.transactionInsertUpdate(); // transaction X
		return md.transactionInsertUpdate3(); // transaction OK
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
