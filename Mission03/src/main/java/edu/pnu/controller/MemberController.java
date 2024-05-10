package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService(new MemberDao());	// Injection
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
	
	@GetMapping(value={"/member/{id}", "/member"})
	public ResponseEntity<?> getMemberById(@PathVariable(required = false) Integer id){
		if(id == null)
			return ResponseEntity.ok(memberService.getAllMember());
		else {			
			MemberVO mem = memberService.getMemberById(id);
			if(mem == null)
				return ResponseEntity.badRequest().body("멤버가 존재하지 않습니다.");
			return ResponseEntity.ok(mem);
		}
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
	
	@DeleteMapping("/member/{id}")
	public ResponseEntity<?> updateMember(@PathVariable Integer id){
		int result = memberService.deleteMember(id);
		if(result == 0)
			return ResponseEntity.badRequest().body("멤버를 삭제할 수 없습니다.");
		return ResponseEntity.ok("멤버를 삭제하였습니다.");
	}
	
}
