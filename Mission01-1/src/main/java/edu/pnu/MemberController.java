package edu.pnu;

import java.util.Date;
import java.util.HashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberController {
	HashMap<Integer, MemberVO> map = new HashMap<Integer,MemberVO>();
	
	public MemberController() {
		for(int i=1; i<=10; i++) {
			map.put(i, MemberVO.builder()
//					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date())
					.build());
		}
	}
	
	@GetMapping("/members")
	public HashMap<Integer, MemberVO> getAllMember(){
		return map;
	}
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		return map.get(id);
	}
	
	@PostMapping("/memberJSON")
	public MemberVO addMember(@RequestBody HashMap<Integer, MemberVO> memberVO) {
		for(int key:memberVO.keySet()) {	
			if(getMemberById(key) != null)
				break;
			MemberVO mem = memberVO.get(key);
			mem.setRegidate(new Date());
			map.put(key, mem);
			return mem;
		}
		return null;
	}
	
	@PutMapping("/memberJSON")
	public int updateMember(@RequestBody HashMap<Integer, MemberVO> memberVO) {
		for(int key:memberVO.keySet()) {	
			MemberVO originMem = getMemberById(key);
			if(originMem == null)
				break;
			MemberVO newMem = memberVO.get(key);
			originMem.setName(newMem.getName());
			originMem.setPass(newMem.getPass());
			map.replace(key, originMem);
			return 1;
		}
		return 0;
	}
	
	@DeleteMapping("/member")
	public int remove(Integer id) {
		try {
			return map.remove(id)==null?0:1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
}
