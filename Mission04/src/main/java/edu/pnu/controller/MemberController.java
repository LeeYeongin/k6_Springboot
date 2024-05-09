package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
}
