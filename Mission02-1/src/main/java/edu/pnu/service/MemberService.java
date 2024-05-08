package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberService {
	List<MemberVO> list = new ArrayList<MemberVO>();
	
	public MemberService() {
		for(int i=1; i<=10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build());
		}
	}
	
	public List<MemberVO> getAllMember() {
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null)
			return null;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	public int updateMember(MemberVO memberVO) {
		MemberVO mem = getMemberById(memberVO.getId());
		if(mem == null)
			return 0;
		mem.setName(memberVO.getName());
		mem.setPass(memberVO.getPass());
		return 1;
	}
	
	public int deleteMember(Integer id) {
		try {
			if(getMemberById(id) == null)
				return 0;
			list.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}
}
