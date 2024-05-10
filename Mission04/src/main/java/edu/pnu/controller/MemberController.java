package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("MemberController 실행");
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable Integer id){
		MemberVO mem = null;
		if(mem == null)
			ResponseEntity.badRequest();
		
		return ResponseEntity.ok(memberService.getMemberById(id));
	}
	
	@PostMapping("/memberJSON")
	public ResponseEntity<?> addMember(@RequestBody MemberVO memberVO){
		int result = memberService.addMember(memberVO);
		if(result == 0)
			return ResponseEntity.badRequest().body("새로운 멤버를 추가할 수 없습니다.");
		return ResponseEntity.ok("멤버를 추가하였습니다.");
	}
	
	@PutMapping("/memberJSON")
	public ResponseEntity<?> updateMember(@RequestBody MemberVO memberVO){
		int result = memberService.updateMember(memberVO);
		if(result == 0)
			return ResponseEntity.badRequest().body("멤버가 아니라 정보를 변경할 수 없습니다.");
		return ResponseEntity.ok("멤버를 수정하였습니다.");
	} 
}
