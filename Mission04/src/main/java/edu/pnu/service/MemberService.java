package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private LogDao logDao;

	public List<MemberVO> getAllMember(){
		Map<String, Object> result = memberDao.getAllMember();
		
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		int logResult = logDao.addLog(method, sql, success);
		
		if(logResult == 0) {
			System.out.println("Log 기록 실패");
		}else {
			System.out.println("Log 기록 성공");
		}
		
		// 작성 중
		return (List<MemberVO>)result.get("list");
	}
	
	public MemberVO getMemberById(Integer id) {
		Map<String, Object> result = memberDao.getMemberById(id);
		
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		int logResult = logDao.addLog(method, sql, success);
		
		if(logResult == 0) {
			System.out.println("Log 기록 실패");
		}else {
			System.out.println("Log 기록 성공");
		}
		
		return (MemberVO)result.get("member");
	}
	
	public int addMember(MemberVO memberVO) {
		Map<String, Object> result = memberDao.addMember(memberVO);
		
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		int logResult = logDao.addLog(method, sql, success);
		
		if(logResult == 0) {
			System.out.println("Log 기록 실패");
		}else {
			System.out.println("Log 기록 성공");
		}
		
		return (int)result.get("result");
	}
	
	public int updateMember(MemberVO memberVO) {
		Map<String, Object> result = memberDao.updateMember(memberVO);
		
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		int logResult = logDao.addLog(method, sql, success);
		
		if(logResult == 0) {
			System.out.println("Log 기록 실패");
		}else {
			System.out.println("Log 기록 성공");
		}
		
		return (int)result.get("result");
	}
	
	public int deleteMember(Integer id) {
		Map<String, Object> result = memberDao.deleteMember(id);
		
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		int logResult = logDao.addLog(method, sql, success);
		
		if(logResult == 0) {
			System.out.println("Log 기록 실패");
		}else {
			System.out.println("Log 기록 성공");
		}
		
		return (int)result.get("result");
	}
}
