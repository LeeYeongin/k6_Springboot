package edu.pnu;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)	// default값은 mock
@AutoConfigureMockMvc
public class MemberTest {

	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void testGetAllMember() {
		Map<String, Object> map = memberDao.getAllMember();
		
		List<MemberVO> list = (List<MemberVO>) map.get("list");
		for(MemberVO member: list)
			System.out.println(member);
				
	}
}
