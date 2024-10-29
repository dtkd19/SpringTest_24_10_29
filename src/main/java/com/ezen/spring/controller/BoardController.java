package com.ezen.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Controller
public class BoardController {
	/* 생성자 주입시 객체는 final로 생성 */
	private final BoardService bsv;

	// return void => 온 경로 그대로 리턴 /board/register => /board/register.jsp 
	@GetMapping("/register")
	public void register() {}

	@PostMapping("/insert")
	public String insert(BoardVO bvo) {
		log.info(">>> insert bvo > {} ", bvo);
		int isOk = bsv.insert(bvo);
		
		log.info(" insert >> {} ", ( isOk >0? "성공" : "실패" ));
		// 컨트롤러의 mapping 위치로 연결할 때 redirect:
		return "redirect:/";
		
	}
	@GetMapping("/list")
	public String list(Model m) {
		//request.setAttrbute()
		//Model 객체가 해당일을 대신해줌
		
		List<BoardVO> list = bsv.getList();
		
		m.addAttribute("list",list);
		
		return "board/list";
		
	}

	
	

}
