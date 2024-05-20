package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByUsername(String username);
}
