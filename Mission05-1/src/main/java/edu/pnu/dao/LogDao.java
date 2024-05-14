package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogDao {
	
	private final DataSource dataSource;
	
	public int addLog(String method, String sql, boolean success) {
		PreparedStatement psmt = null;
		
		String query = "INSERT INTO dblog(method, sqlstring, success) VALUES(?,?,?)";
		int result;
		try {
			psmt = dataSource.getConnection().prepareStatement(query);

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
