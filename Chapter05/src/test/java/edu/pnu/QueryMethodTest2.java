package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest2 {
	@Autowired private BoardRepository boardRepo;
	
	@BeforeEach	// 테스트 메서드 실행 이전에 수행
	public void dataPrepare() {
		Random rnd = new Random();
		for(int i=1; i<=100; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(Math.abs(rnd.nextLong()%101L));
			boardRepo.save(board);
		}
	}
	
//	@Test
	public void testFindByTitleContaining() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		
		System.out.println("[findByTitleContaining] 검색 결과");
		for(Board board : boardList) {
			System.out.println("---> "+ board);
		}
	}
	
//	@Test
	public void testFindByTitleContainingAndCntGreaterThan() {
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		
		System.out.println("[findByTitleContainingAndCntGreaterThan] 검색 결과");
		for(Board board : boardList) {
			System.out.println("---> "+ board);
		}
	}
	
//	@Test
	public void testFindByCntBetweenOrderBySeqAsc() {
		List<Board> boardList = boardRepo.findByCntBetweenOrderBySeqAsc(10L, 50L);
		
		System.out.println("[findByCntBetweenOrderBySeqAsc] 검색 결과");
		for(Board board : boardList) {
			System.out.println("---> "+ board);
		}
	}
	
	@Test
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		
		System.out.println("[findByTitleContainingOrContentContainingOrderBySeqDesc] 검색 결과");
		for(Board board : boardList) {
			System.out.println("---> "+ board);
		}
	}
	
	

}
