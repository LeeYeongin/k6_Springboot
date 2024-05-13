package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j	// IoC에 올라가는 Bean 객체에만 의미가 있음
@RestController
public class BoardController {

	@GetMapping("/hello")
	public String hello(String name) {
		log.debug("name:" + name);
		return "Hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setWirter("테스터");
		return board;
	}
	
}

/*
 * [ 빌드 ]
 * 1. 프로젝트 우클릭, Run As > Maven install
 * 2. BUILD SUCCESS
 * 3. target> jar파일 우클릭 > show in > terminal
 * 4. java -jar Chapter03-0.0.1-SNAPSHOT.jar -> 서버 구동 완료
 * 5. url주소로 접근
*/