package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boards")
	public List<Board> getBoard(){
		return boardService.getBoard();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoardById(@PathVariable Long seq) {
		return boardService.getBoardById(seq);
	}
	
	@GetMapping("/boardMem/{username}")
	public List<Board> getBoardByUsername(@PathVariable String username) {
		return boardService.getBoardByUsername(username);
	}
	
}
