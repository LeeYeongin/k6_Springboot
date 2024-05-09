package edu.pnu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	private MemberDao memberDao;
	private LogDao logDao;
	
	public MemberService() {
		memberDao = new MemberDao();
		logDao = new LogDao();
	}
	
	public List<MemberVO> getAllMember(){
		Map<String, Object> result = memberDao.getAllMember();
		String method = (String) result.get("method");
		String sql = (String) result.get("sql");
		boolean success = (boolean) result.get("success");
		
		// 작성 중
		return (List<MemberVO>)result.get("member");
	}
}
