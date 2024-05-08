package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	MemberDao memberDao;
	
	public MemberService() {
		
	}
	
	public List<MemberVO> getAllMember(){
		memberDao = new MemberDao();
		List<MemberVO> list = memberDao.getAllMember();
		memberDao.close();
		
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		memberDao = new MemberDao();
		MemberVO mem = memberDao.getMemberById(id);
		memberDao.close();
		
		return mem;
	}
}
