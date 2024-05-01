package com.oracle.oBootBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.oracle.oBootBoard.dto.BDto;

public class JdbcDao implements BDao {
	// JDBC 사용 (final은 안걸어도 되지만 값을 변화시킬 필요가 없어서 걸어줌)
	private final DataSource dataSource;

	public JdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection(); // 오름차순,내림차순으로 정렬시킨건 댓글때문
			String sql = "select * from mvc_board order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement(sql);
			System.out.println("BDao query --> " + sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				BDto bDto = new BDto(resultSet.getInt("bId"), resultSet.getString("bName"),
						resultSet.getString("bTitle"), resultSet.getString("bContent"), resultSet.getTimestamp("bDate"),
						resultSet.getInt("bHit"), resultSet.getInt("bGroup"), resultSet.getInt("bStep"),
						resultSet.getInt("bIndent"));

				bList.add(bDto);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return bList;
	}

	@Override
	public void write(String bName, String bTitle, String bContent) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		// 1. Insert into mvc_board (HW1)
		// 2. preparedstatement
		// 3. mvc_board_seq
		// 4. bId , bGroup 같게
		// 5. bStep, bIndent, bDate --> 0, 0 , sysdate
		String sql = "insert into mvc_board values(mvc_board_seq.nextval,?,?,?,sysdate,0,mvc_board_seq.nextval,0,0)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public BDto contentView(int bId) {
		upHit(bId);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select bId, bHit, bName, bTitle, bContent from mvc_board where bId = ?";

		BDto bDto = new BDto();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bDto.setbId(bId);
				bDto.setbHit(rs.getInt("bHit"));
				bDto.setbName(rs.getString("bName"));
				bDto.setbTitle(rs.getString("bTitle"));
				bDto.setbContent(rs.getString("bContent"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bDto;
	}

	private void upHit(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update mvc_board set bhit = bhit + 1 where bid=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void modify(int bId, String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=?";

		System.out.println("modify query-> " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	   public BDto reply_view(int bId) {
	      BDto bDto = new BDto();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = "select * from mvc_board where bId=?";
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, bId);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            bDto.setbId(rs.getInt("bId"));
	            bDto.setbName(rs.getString("bName"));
	            bDto.setbTitle(rs.getString("bTitle"));
	            bDto.setbContent(rs.getString("bContent"));
	            bDto.setbDate(rs.getTimestamp("bDate"));
	            bDto.setbHit(rs.getInt("bHit"));
	            bDto.setbGroup(rs.getInt("bGroup"));
	            bDto.setbStep(rs.getInt("bStep"));
	            bDto.setbIndent(rs.getInt("bIndent"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch(Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return bDto;
	   }

	@Override
	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {
//	    [1] bId SEQUENCE = bGroup 
//	    [2] bName, bTitle, bContent -> request Value
//	    [3] 홍해 기적
//	    [4] bStep / bIndent   + 1
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		replyShape(bGroup, bStep);
		
		String sql = "insert into mvc_board"
				+ "(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval,?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bGroup);
			pstmt.setInt(5, bStep+1);
			pstmt.setInt(6, bIndent+1);
			
			int rn = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

	private void replyShape(int bGroup, int bStep) {
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = getConnection();
	         String sql = "update mvc_board set bStep = bStep + 1"
	               + " where bGroup = ? and bStep > ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, bGroup);
	         pstmt.setInt(2, bStep);
	         
	         int result = pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }

		
	}

	@Override
	public void delete(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from mvc_board where bId = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
