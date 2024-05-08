package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getMemberById(Integer id){
		MemberVO mem = memberService.getMemberById(id);
		if(mem == null)
			return ResponseEntity.badRequest().body("멤버가 존재하지 않습니다.");
		return ResponseEntity.ok(mem);
	}
}
