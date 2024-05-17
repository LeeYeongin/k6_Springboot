package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String searchKeyword);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	List<Board> findByTitleContaining(String searchKeyword);
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long num);
	List<Board> findByCntBetweenOrderBySeqAsc(Long smallNum, Long bigNum);
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String searchKeyword1, String searchKeyword2);
	// paging
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}
