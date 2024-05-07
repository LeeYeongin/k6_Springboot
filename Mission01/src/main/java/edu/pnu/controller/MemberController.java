package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	List<MemberVO> list = new ArrayList<MemberVO>();

	public MemberController() {
		for (int i = 1; i <= 10; i++) {
			list.add(MemberVO.builder().id(i).name("name" + i).pass("pass" + i).regidate(new Date()).build());
		}
	}

	// 검색(Read - select)
	@GetMapping("/members")
	public List<MemberVO> getAllMember() {
		return list;
	}

	// 검색(Read - select)
//	@GetMapping("/members")
//	public ResponseEntity<?> getAllMember() {
//		return ResponseEntity.ok(list);	// 응답코드를 함께 정해서 return 가능
////		return ResponseEntity.badRequest().body(list);
//	}

	// 검색(Read - select)
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		for (MemberVO member : list) {
			if (member.getId() == id) {
				return member;
			}
		}
		return null;
	}

	// 입력(Create -insert)
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) { // request body에 숨겨 data 전송
		if (getMemberById(memberVO.getId()) != null)
			return null;
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}

	/*
	 * 위 코드를 아래와 같이도 작성 가능: 입력(Create -insert)
	 * 
	 * @PostMapping("/member") public MemberVO addMember(MemberVO memberVO) { if
	 * (getMemberById(memberVO.getId()) != null) return null;
	 * memberVO.setRegidate(new Date()); list.add(memberVO); return memberVO; }
	 * 
	 * @PostMapping("/memberJSON") public MemberVO addMemberJSON(@RequestBody
	 * MemberVO memberVO) { return addMember(memberVO); }
	 */

	// 수정(Update - update)
	/*
	 * @PutMapping("/member") public int updateMember(MemberVO memberVo) { MemberVO
	 * m = getMemberById(memberVo.getId()); if (m == null) return 0;
	 * m.setName(memberVo.getName()); m.setPass(memberVo.getPass()); return 1; }
	 */

	// put도 requestbody에 데이터를 담아 전송가능
	@PutMapping("/memberJSON")
	public int updateMemberJSON(@RequestBody MemberVO memberVo) {
		MemberVO m = getMemberById(memberVo.getId());
		if (m == null)
			return 0;
		m.setName(memberVo.getName());
		m.setPass(memberVo.getPass());
		return 1;
	}

	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

}
