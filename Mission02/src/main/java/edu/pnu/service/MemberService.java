package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	List<MemberVO> list = new ArrayList<MemberVO>();
	
	public MemberService() {
		for(int i=1; i<=10; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date())
					.build());
		}
	}
	
	public List<MemberVO> getAllMembers(){
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		for(MemberVO member: list) {
			if(member.getId() == id)
				return member;
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
	
	public int updateMember(MemberVO member) {
		MemberVO mem = getMemberById(member.getId());
		if(mem == null)
			return 0;
		
		mem.setName(member.getName());
		mem.setPass(member.getPass());
		return 1;
	}
	
	public int deleteMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
