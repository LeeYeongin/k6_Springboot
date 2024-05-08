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
	public Statement stmt;
	public ResultSet rs;
	public PreparedStatement psmt;

	public MemberDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd");
			stmt = con.createStatement();	// 쿼리문 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getAllMember(){
		List<MemberVO> list = new ArrayList<>();
		
		// 쿼리문 준비
		String query = "select * from member ";
		
		try { 
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
		}
		
		return list; // 목록 반환	
	}
	
	public MemberVO getMemberById(Integer id) {
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
		} 
		
		return memberVO;
	}
	
	

	// 연결 해제(자원 반납)
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

			System.out.println("JDBC 자원 해제");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
