package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {
	
	@Autowired
	private MemberRepository memberRepo;
	 
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		Member member1 = new Member(); // 왜 별도로 Autowired 하지 않지? Repo를 연동해놓을 때 자동 연동되기 때문일까?
		member1.setId("member1");
		member1.setName("둘리");
		member1.setPassword("member1111");
		member1.setRole("ROLE_UESR");
		memberRepo.save(member1);
		
		Member member2 = new Member(); 
		member2.setId("member2");
		member2.setName("도우너");
		member2.setPassword("member2222");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		
		for(int i=1 ; i<=3 ; i++) {
			Board board = new Board();
			board.setWriter("둘리");
			board.setTitle("둘리가 등록한 게시글"+i);
			board.setContent("둘리가 등록한 게시글 내용"+i);
			boardRepo.save(board);
		}
		
		for(int i=1 ; i<=3 ; i++) {
			Board board = new Board();
			board.setWriter("도우너");
			board.setTitle("도우너가 등록한 게시글"+i);
			board.setContent("도우너가 등록한 게시글 내용"+i);
			boardRepo.save(board);
		}
	}
	
	
}
