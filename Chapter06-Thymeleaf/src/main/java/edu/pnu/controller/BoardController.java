package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")	// 모델에 member라고 데이터가 저장되면 자동으로 세션에 저장
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member")	// 생성하여 세션에 저장하는 역할 -> 없으면 error발생
	public Member setMember() {
		return new Member();
	}
	
	@GetMapping("/getBoardList")	// @ModelAttribute("member") Member member -> session 저장되어 있는 member변수를 읽어옴
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) { // Model은 view에 넘겨줄 클래스를 저장하는 역할
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardList", boardList);	// boardList라는 이름으로 저장
		return "getBoardList";	// 이름을 return -> application.properties의 prefix + 이름 + suffix
	}
	
//	@GetMapping("/getBoardList")
//	public ModelAndView getBoardList(Board board) { // Model은 view에 넘겨줄 클래스를 저장하는 역할	
//		List<Board> boardList = boardService.getBoardList(board);	
//	
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("boardList", boardList);
//		mv.setViewName("getBoardList");
//		
//		return mv;
//	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}
	
//	@GetMapping("/getBoard")
//	public ModelAndView getBoard(Long seq) {
//		Board b = boardService.getBoard(seq);
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("board", b);
//		mv.setViewName("getBoard");
//		
//		return mv;
//	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.updateBoard(board);
		return "redirect:getBoardList";	// forward하면 post -> post와 같이 같은 method로 이동
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
