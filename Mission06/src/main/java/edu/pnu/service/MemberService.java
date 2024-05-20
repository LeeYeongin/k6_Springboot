package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public List<Member> getMember() {
		return memberRepo.findAll();
	}

	public Member getMemberById(String username) {
		return memberRepo.findById(username).get();
	}

	public List<Member> getMemberByRole(String role) {
		return memberRepo.findByRole(role);
	}
	
	public Member addMember(Member member) {
		return memberRepo.save(member);
	}

	public Member updateMember(Member member) {
		Member m = memberRepo.findById(member.getUsername()).get();
		m.update(member);
		return memberRepo.save(m);
	}

	public Member deleteMember(String username) {
		Member m = memberRepo.findById(username).get();
		memberRepo.deleteById(username);
		return m;
	}


}
