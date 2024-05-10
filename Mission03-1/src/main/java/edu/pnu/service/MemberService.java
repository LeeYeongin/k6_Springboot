package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired	// boot가 자동으로 DI해줌
	private MemberDao memberDao;
	
	public MemberService() {
		System.out.println("MemberService 실행");
	}
//	
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
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
