package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
}
