package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	MemberDao memberDao;
	
	public MemberService(MemberDao memgerDao) {
		this.memberDao = memgerDao;
	}
	
//	public MemberService() {
//		memberDao = new MemberDao();
//	}
	
	public List<MemberVO> getAllMember(){
		List<MemberVO> list = memberDao.getAllMember();
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		MemberVO mem = memberDao.getMemberById(id);
		return mem;
	}
	
	public int addMember(MemberVO memberVO) {
		int result = memberDao.addMember(memberVO);
		return result;
	}
	
	public int updateMember(MemberVO memberVO) {
		int result = memberDao.updateMember(memberVO);
		return result;
	}
	
	public int deleteMember(Integer id) {
		int result = memberDao.deleteMember(id);
		return result;
	}
}
