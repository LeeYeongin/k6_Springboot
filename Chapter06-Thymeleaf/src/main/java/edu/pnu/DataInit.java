package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner{
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Member m1 = Member.builder()
						.id("member1")
						.name("둘리")
						.password("member111")
						.role("ROLE_USER")
						.build();
		Member m2 = Member.builder()
				.id("member2")
				.name("도우너")
				.password("member222")
				.role("ROLE_ADMIN")
				.build();
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		
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
