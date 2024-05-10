package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	
	private final DataSource dataSource;	// 연결정보는 src/main/resources/application.properties에 있음
	
	// Read: 모든 멤버 읽어오기
	public Map<String, Object> getAllMember(){
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = true;
		
		List<MemberVO> list = new ArrayList<>();
		
		// 쿼리문 준비
		String query = "select * from member";
		
		try { 
			stmt = dataSource.getConnection().createStatement();	// 쿼리문 생성
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
		map.put("method", "GET");
		map.put("sql", query);
		map.put("success", flag);
		
		return map; // 목록 반환	
	}
	
	// Read: id로 원하는 멤버 정보 찾아오기
	public Map<String, Object> getMemberById(Integer id){
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean flag = true;
		
		MemberVO memberVO = null;
		
		// 쿼리문 준비
		String query = "select * from member where id=?";
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
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
			flag = false;
		} finally {
			query = psmt.toString();
			int idx = query.indexOf(':');
			query = query.substring(idx+2);
			
			System.out.println(query);
			
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("member", memberVO);
		map.put("method", "GET");
		map.put("sql", query);
		map.put("success", flag);
		
		return map;
	}
	
	
	// Add: 멤버 추가하기
	public Map<String, Object> addMember(MemberVO memberVO) {
		PreparedStatement psmt = null;

		String query = "INSERT INTO member(pass, name) VALUES(?,?)";
		int result = 0;
		boolean flag = true;
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);

			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			result = psmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
			result = 0;
		} 
		finally {
			query = psmt.toString();
			int idx = query.indexOf(':');
			query = query.substring(idx+2);
			
			System.out.println(query);
			
			try {
				if(psmt != null)
					psmt.close();
				System.out.println("JDBC 자원 해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("method", "POST");
		map.put("sql", query);
		map.put("success", flag);
		
		return map;	
	}
	
	// Update: 정보 변경하기
	public Map<String, Object> updateMember(MemberVO memberVO) {
		PreparedStatement psmt = null;

		String query = "UPDATE Member " 
				+ "SET name = CASE WHEN ? IS NOT NULL THEN ? ELSE name END, "
				+ "pass = CASE WHEN ? IS NOT NULL THEN ? ELSE pass END " 
				+ "WHERE id = ?";
		int result;
		boolean flag = true;
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getName());
			psmt.setString(3, memberVO.getPass());
			psmt.setString(4, memberVO.getPass());
			psmt.setInt(5, memberVO.getId());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
			flag = false;
		} finally {
			query = psmt.toString();
			int idx = query.indexOf(':');
			query = query.substring(idx+2);
			
			System.out.println(query);
			
			try {
				if(psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("method", "PUT");
		map.put("sql", query);
		map.put("success", flag);
		
		return map;	
	}
}
