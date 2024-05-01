package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	// Mybatis DB 연동
	private final SqlSession session;

	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl Start total...");

		try {
			totEmpCount = session.selectOne("com.oracle.oBootMybatis01.EmpMapper.empTotal");
			System.out.println("EmpDaoImpl totalEmp totEmpCount->" + totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception->" + e.getMessage());
		}
		return totEmpCount;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp Start ...");
		try {
			// Map ID parameter
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList.size()->" + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpDaoImpl detail start..");
		// Emp emp = null;
		Emp emp = new Emp();
		try {
			// mapper ID , Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->" + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->" + e.getMessage());
		}
		return emp;
	}

	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpDaoImpl update start..");
		int updateCount = 0;
		try {
			updateCount = session.update("tkEmpUpdate", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl updateEmp Exception->" + e.getMessage());
		}
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listManager Start ...");
		try {
			// emp 관리자만 Select Naming Rule
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		System.out.println("EmpDaoImpl insert Start...");
		try {
			result = session.insert("insertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insert Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<Emp> empSearchList3(Emp emp) {
		List<Emp> empSearchList3 = null;
		System.out.println("EmpDaoImpl empSearchList3 Start ...");
		System.out.println("EmpDaoImpl empSearchList3 emp->" + emp);
		try {
			// keyword검색
			// Naming Rule Map ID parameter
			empSearchList3 = session.selectList("tkEmpSearchList3", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->" + e.getMessage());
		}
		return empSearchList3;

	}

	@Override
	public int condTotalEmp(Emp emp) {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl Start condTotalEmp...");
		System.out.println("EmpDaoImpl Start emp->" + emp);

		try {
			totEmpCount = session.selectOne("condEmpTotal", emp);

			System.out.println("EmpDaoImpl totalEmp totEmpCount->" + totEmpCount);

		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception->" + e.getMessage());
		}
		return totEmpCount;
	}

	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpDaoImpl delete start..");
		int result = 0;
		System.out.println("EmpDaoImpl delete empno->" + empno);
		try {
			result = session.delete("deleteEmp", empno);
			System.out.println("EmpDaoImpl delete result->" + result);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl listEmpDept Start...");
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size()->" + empDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->" + e.getMessage());
		}
		return empDept;
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName Start...");
		String resultStr = "";
		try {
			System.out.println("EmpDaoImpl deptName deptno -> " + deptno);
			resultStr = session.selectOne("tkDeptName", deptno);
			System.out.println("EmpDaoImpl deptName resultStr -> " + resultStr);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception -> " + e.getMessage());
		}
		return resultStr;
	}

}
