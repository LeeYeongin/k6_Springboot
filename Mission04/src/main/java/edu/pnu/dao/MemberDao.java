package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> getAllMember(){
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = true;
		
		List<MemberVO> list = new ArrayList<>();
		
		// 쿼리문 준비
		String query = "select * from member";
		
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
			flag=false;
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("sql", query);
		map.put("result", flag);
		
		return map; // 목록 반환	
	}
}
