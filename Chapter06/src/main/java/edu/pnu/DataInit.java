package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner{
	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int i=1; i<=10; i++) {
			boardRepo.save(Board.builder()
					.title("게시판 프로그램 테스트"+i)
					.content("게시판 프로그램 테스트입니다."+i)
					.writer("도우너")
					.build()
					);
		}
	}
}
