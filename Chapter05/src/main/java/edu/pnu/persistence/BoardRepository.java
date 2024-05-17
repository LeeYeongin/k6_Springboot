package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	// Query문
	// 1. 위치 기반 파라미터
//	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
//	List<Board> queryAnnotationTest1(String searchKeyword);
	// 2. 이름 기반 파리미터
	@Query("SELECT b FROM Board b "	// 필드 속성(테이블명)은 class명(변수명)이랑 같아야함
			+ "WHERE b.title like %:searchKeyword% "
			+ "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
	
	// 특정 변수만 조회하기
	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b "
			+ "WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(@Param("searchKeyword")String searchKeyword);
	
	// 네이티브 쿼리
	@Query(value="select seq, title, writer, create_date "
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest3(String searchKeyword); 
	
}
