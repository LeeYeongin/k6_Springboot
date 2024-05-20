package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public List<Member> getMember(){
		return memberService.getMember();
	}
	
	@GetMapping("/member/{username}")
	public Member getMemberById(@PathVariable String username){
		return memberService.getMemberById(username);
	}
	
	@GetMapping("/memberRole/{role}")
	public List<Member> getMemberByRole(@PathVariable String role){
		return memberService.getMemberByRole(role);
	}
	
	@PostMapping("/member")
	public Member addMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	@PutMapping("/member")
	public Member updateMember(@RequestBody Member member) {
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{username}")
	public Member deleteMember(@PathVariable String username) {
		return memberService.deleteMember(username);
	}
	
	
}
