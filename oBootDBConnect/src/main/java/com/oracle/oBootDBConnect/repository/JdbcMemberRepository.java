package com.oracle.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.oBootDBConnect.domain.Member1;

public class JdbcMemberRepository implements MemberRepository {
	// JDBC 사용
	private final DataSource dataSource;
	@Autowired
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	@Override
	public Member1 save(Member1 member1) {
		String sql = "insert into member7(id,name) values(member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql->"+sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member1.getName());
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepository pstmt.executeUpdate After");
			return member1;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// close(conn, pstmt, rs);
		}
	}

	@Override
	public List<Member1> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
