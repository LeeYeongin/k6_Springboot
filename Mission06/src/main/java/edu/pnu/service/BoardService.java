package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	public List<Board> getBoard() {
		return boardRepo.findAll();
	}

	public Board getBoardById(Long seq) {
		return boardRepo.findById(seq).get();
	}

	public List<Board> getBoardByUsername(String username) {
		return boardRepo.findByUsername(username);
	}

}
