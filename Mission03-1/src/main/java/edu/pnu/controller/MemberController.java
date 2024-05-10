package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruby.TestService;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

/*
 * 1. 필드변수에 Autowired
 * 2. 생성자에 파라미터 설정
 * 3. setter를 만들어서 Autowired
 * 4.@RequiredArgsConstructor - final로 설정
 */

@RestController
//방법 - 4 (기본생성자 or RequiredArgsConstructor 중 하나만 사용 가능)
@RequiredArgsConstructor	// final로 선언된 변수 생성자 생성 
public class MemberController {
	
	// 방법 - 1
//	@Autowired	
//	private MemberService memberService;
//	@Autowired
//	private TestService testService;
	
	// 방법 - 4
	private final MemberService memberService;
	private final TestService testService;
	
	
//	public MemberController() {	// 방법 - 1
//		System.out.println("MemberController 실행");
//		
//		// Dependency Injection
//		//memberService = new MemberService();	
//		//memberService.setMemberDao(new MemberDao());
//	}
	

//	public MemberController(MemberService memberService) {	// 방법 - 2
//		this.memberService = memberService;
//		System.err.println("MemberController 생성");
//	}
	
//	@Autowired
//	public void setMemberService(MemberService memberService) {	// 방법 - 3
//		this.memberService = memberService;
//		System.out.println("MemberController 생성");
//	}
	
	
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
