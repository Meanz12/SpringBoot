package com.rubypaper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rubypaper.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rubypaper.service.BoardService;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // defined_port:프로퍼티 파일에 설정된 포트 사용, none:서블릿 기반 환경 자체를 구성X
@AutoConfigureMockMvc
public class BoardControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService boardService;
	
	@Test
    public void testHello() throws Exception {
		 when(boardService.hello("둘리")).thenReturn("Hello : 둘리");

	        mockMvc.perform(get("/hello").param("name", "둘리"))
	                .andExpect(status().isOk())
	                .andExpect(content().string("Hello : 둘리"))
	                .andDo(print());
    }
}
