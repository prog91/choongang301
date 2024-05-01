package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {
	
	// Transaction을 위한 설정
	private final PlatformTransactionManager transactionManager;

	// Mybatis DB 연동
	private final SqlSession session;

	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl id->" + id);
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl memCount Exception->" + e.getMessage());
		}

		return result;
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("Member1DaoImpl listMem start...");
		List<Member1> listMember1 = null;
		try {
			listMember1 = session.selectList("listMember1", member1);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl listMem Exception->" + e.getMessage());
		}
		return listMember1;
	}

	@Override
	public int transactionInsertUpdate() {
		int result = 0;
		System.out.println("Member1DaoImpl transactionInsertUpdate Start..");
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();

		try {
			// 두개의 transaction Test 성공과 실패
			// 결론 --> SqlSession은 하나 실행 할 때마다 자동 Commit
			member1.setId("1005");
			member1.setPassword("2345");
			member1.setName("강유6");

			result = session.insert("insertMember1", member1);
			System.out.println("Member1DaoImpl transactionInsertUpdate member1 result -> " + result);
			// 같은 PK로 일부러 실패유도
			member2.setId("1005");
			member2.setPassword("3457");
			member2.setName("이순신7");
			result = session.insert("insertMember1", member2);
			System.out.println("Member1DaoImpl transactionInsertUpdate member2 result -> " + result);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl transactionInsertUpdate Exception -> " + e.getMessage());
			result = -1;
		}
		return result;
	}

	@Override
	public int transactionInsertUpdate3() {
		int result = 0;
		System.out.println("Member1DaoImpl transactionInsertUpdate3 Start..");
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();
		
		// Transaction을 위한 설정(JPA에서는 entity manager, MyBatis에서는 transactionManager를 사용)
		TransactionStatus txStatus = 
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			// 두개의 transaction Test 성공과 실패
			// 결론 --> SqlSession은 하나 실행 할 때마다 자동 Commit
			// Transaction관리는 transactionManager의 getTransaction을가지고 상태따라 설정
			member1.setId("1007");
			member1.setPassword("2345");
			member1.setName("강유7");

			result = session.insert("insertMember1", member1);
			System.out.println("Member1DaoImpl transactionInsertUpdate3 member1 result -> " + result);
			// 같은 PK로 일부러 실패유도
			member2.setId("1007");
			member2.setPassword("3457");
			member2.setName("이순신7");
			result = session.insert("insertMember1", member2);
			System.out.println("Member1DaoImpl transactionInsertUpdate3 member2 result -> " + result);
			// 무결성이 지켜지면 commit시키도록 설정
			transactionManager.commit(txStatus);
			
		} catch (Exception e) {
			// exception이 뜨면 rollback되기 위한 설정 -> pk가 같기 때문에 둘 다 db에 올라가지 않는다
			transactionManager.rollback(txStatus);
			System.out.println("Member1DaoImpl transactionInsertUpdate3 Exception -> " + e.getMessage());
			result = -1;
		}
		return result;
	}

}
