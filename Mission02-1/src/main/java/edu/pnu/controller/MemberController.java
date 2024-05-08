package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getMemberById(Integer id){
		MemberVO mem = memberService.getMemberById(id);
		if(mem == null)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버입니다.");
		return ResponseEntity.ok(mem);
	}
	
	@PostMapping("/memberJSON")
	public ResponseEntity<?> addMember(@RequestBody MemberVO memberVO){
		MemberVO mem = memberService.addMember(memberVO);
		if(mem == null)
			return ResponseEntity.badRequest().body("이미 존재하는 id입니다.");
		return ResponseEntity.ok(mem);
	}
	
	@PutMapping("/memberJSON")
	public ResponseEntity<?> updateMember(@RequestBody MemberVO memberVO){
		int result = memberService.updateMember(memberVO);
		if(result == 0)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버입니다.");
		return ResponseEntity.ok("성공적으로 변경되었습니다.");
	}
	
	@DeleteMapping("/member")
	public ResponseEntity<?> deleteMember(Integer id){
		int result = memberService.deleteMember(id);
		if(result == 0)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버입니다.");
		return ResponseEntity.ok("성공적으로 삭제되었습니다.");
	}
	
	
}
