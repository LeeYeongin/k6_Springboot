package edu.pnu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import edu.pnu.domain.BoardVO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)	// default값은 mock
@AutoConfigureMockMvc
public class BoardControllerTest {
	/*
	 *	// MockMVC 방식(WebEnvironment.MOCK)
	 *
	 * @Autowired private MockMvc mockMvc;
	 * 
	 * @Test public void testHello() throws Exception {
	 * mockMvc.perform(get("/hello").param("name", "둘리"))
	 * .andExpect(status().isOk()) .andExpect(content().string("Hello : 둘리"))
	 * .andDo(print()); }
	 */
	
	// 내장 톰캣 테스트
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testHello() throws Exception {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("Hello : 둘리", result);
	}
	
	@Test
	public void testGetBoard() throws Exception {
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWirter());
	}
	
}
