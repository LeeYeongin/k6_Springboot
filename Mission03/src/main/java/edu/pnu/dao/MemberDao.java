package edu.pnu.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDao {
	public Connection con;

	public MemberDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Read: 모든 멤버 읽어오기
	public List<MemberVO> getAllMember(){
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<>();
		
		// 쿼리문 준비
		String query = "select * from member ";
		
		try { 
			stmt = con.createStatement();	// 쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리문 실행
			
			// 반환된 게시물 목록을 List 컬렌션에 추가
			while(rs.next()) {
				list.add(MemberVO.builder()
						.id(rs.getInt(1))
						.pass(rs.getString(2))
						.name(rs.getString(3))
						.regidate(rs.getDate(4))
						.build());
			}
			
		} catch (Exception e) {
			System.out.println("멤버 조회 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(rs != null)
					rs.close();
				System.out.println("JDBC 자원 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; // 목록 반환	
	}
	
	// Read: id와 일치하는 멤버 읽어오기
	public MemberVO getMemberById(Integer id) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberVO memberVO = null;
		
		// 쿼리문 준비
		String query = "select * from member where id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id); // 인파라미터 설정
			rs = psmt.executeQuery();	// 쿼리문 실행
			
			if(rs.next()) {
				memberVO = MemberVO.builder()
						.id(rs.getInt(1))
						.pass(rs.getString(2))
						.name(rs.getString(3))
						.regidate(rs.getDate(4))
						.build();
			}
		} catch (SQLException e) {
			System.out.println("멤버를 아이디값으로 조회 중 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null)
					psmt.close();
				if(rs != null)
					rs.close();
				System.out.println("JDBC 자원 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberVO;
	}
	
	// Add: 멤버 추가하기
	public int addMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		
		String query = "INSERT INTO member(id, pass, name) VALUES(?,?,?)";
		int result;
		try {
			psmt = con.prepareStatement(query);
			// 1. 아이디, 아이디, 비밀번호를 넘겨받을 경우
//			psmt.setInt(1, memberVO.getId());
//			psmt.setString(2, memberVO.getPass());
//			psmt.setString(3, memberVO.getName());
			
			// 2. 이름, 비밀번호를 넘겨받을 겨우
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			result = psmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(psmt != null)
					psmt.close();
				System.out.println("JDBC 자원 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Update: 정보 변경하기
	public int updateMember(MemberVO memberVO) {
		PreparedStatement psmt = null;
		
//		String query = "UPDATE member SET name=?, pass=? WHERE id=?";
		String query = "UPDATE Member " 
				+ "SET name = CASE WHEN ? IS NOT NULL THEN ? ELSE name END, "
				+ "pass = CASE WHEN ? IS NOT NULL THEN ? ELSE pass END " 
				+ "WHERE id = ?";
		int result;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getName());
			psmt.setString(3, memberVO.getPass());
			psmt.setString(4, memberVO.getPass());
			psmt.setInt(5, memberVO.getId());
			
			result = psmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Delete 
	public int deleteMember(Integer id) {
		PreparedStatement psmt = null;
		
		String query = "DELETE member WHERE id = ?";
		int result;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			
			result = psmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
