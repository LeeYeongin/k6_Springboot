package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

public class LogDao {
	public Connection con;
	
	public LogDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd");
			System.out.println("연결에 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addLog(String method, String sql, boolean success) {
		PreparedStatement psmt = null;
		
		String query = "INSERT INTO dblog(method, sqlstring, success) VALUES(?,?,?)";
		int result;
		try {
			psmt = con.prepareStatement(query);

			psmt.setString(1, method);
			psmt.setString(2, sql);
			psmt.setBoolean(3, success);
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
}
