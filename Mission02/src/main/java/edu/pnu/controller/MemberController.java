package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getAllMembers(){
		return memberService.getAllMembers();
	}
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return memberService.getMemberById(id);
	}
	
	@PostMapping("/memberJSON")
	public MemberVO addMember(@RequestBody MemberVO member) {
		return memberService.addMember(member);
	}
	
	@PutMapping("/memberJSON")
	public int updateMember(@RequestBody MemberVO member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member")
	public int deleteMember(Integer id) {
		return memberService.deleteMember(id);
	}

}
