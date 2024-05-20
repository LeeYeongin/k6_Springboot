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
		for(int i=1; i<=5; i++) {
			Member mem1 = Member.builder()
					.username("member"+i)
					.password("password"+i)
					.role("User")
					.build();
			memberRepo.save(mem1);
			
			for(int j=1; j<5; j++) {
				boardRepo.save(Board.builder()
						.title("title1"+i)
						.content("content1"+i)
						.member(mem1)
						.build()
						);
			}
		}
		
		Member mem2 = Member.builder()
				.username("member6")
				
				.password("password6")
				.role("Admin")
				.build();
		memberRepo.save(mem2);
	
		for(int i=1; i<5; i++) {
			boardRepo.save(Board.builder()
					.title("title2"+i)
					.content("content2"+i)
					.member(mem2)
					.build()
					);
		}
	}
}
