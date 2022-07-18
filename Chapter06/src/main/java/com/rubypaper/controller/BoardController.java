package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@ModelAttribute("member") // 리턴한 객체가 세션에 등록됨
	public Member setMember() {
		return new Member();
	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model,Board board) { // 세션의 member 객체를 변수에 바인딩
		if(member.getId() == null) { return "redirect:login"; }
		List<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList",boardList);
		return "getBoardList";
	}
	
	 @GetMapping("/insertBoard")
	 public String insertBoardView(@ModelAttribute("member") Member member) {
		if(member.getId() == null) { return "redirect:login"; }
	    return "insertBoard";
	 }

	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) { return "redirect:login"; }
	   boardService.insertBoard(board);
	   return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board,Model model) {
		if(member.getId() == null) { return "redirect:login"; }
		model.addAttribute("board",boardService.getBoard(board));
		return "getBoard";
	}	    
    @PostMapping("/updateBoard")
    public String updateBoard(@ModelAttribute("member") Member member,Board board) {
    	if(member.getId() == null) { return "redirect:login"; }
        boardService.updateBoard(board);
        return "forward:getBoardList";
    }
    @GetMapping("/deleteBoard")
    public String deleteBoard(@ModelAttribute("member") Member member,Board board) {
    	if(member.getId() == null) { return "redirect:login"; }
        boardService.deleteBoard(board);
        return "forward:getBoardList";
    }
    
    @GetMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("greeting", "Hello 타임리프");
    }
}
