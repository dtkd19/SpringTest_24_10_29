package com.ezen.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spring.domain.BoardVO;
import com.ezen.spring.domain.PagingVO;
import com.ezen.spring.handler.PagingHandler;
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
	public String list(Model m, PagingVO pgvo ) {
		//request.setAttrbute()
		//Model 객체가 해당일을 대신해줌
		
//		PagingVO pgvo = new PagingVO();
		List<BoardVO> list = bsv.getList(pgvo);
		
		// totalCount 구해서 PagingHandler에 매개변수로 전달 
		int totalCount = bsv.getTotal(pgvo);
		
		log.info(">>> totalCount > {}" , totalCount);
		
		PagingHandler ph = new PagingHandler(totalCount, pgvo);
			
		m.addAttribute("list",list);
		m.addAttribute("ph",ph);
		return "/board/list";
		
	}
	
	
	// @requestParam("bno") int bno => 전달되는 파라미터가 여러개일 경우 이름을 명시
	// return void : 요청 경로로 응답을 그대로 보냄.
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, int bno, HttpServletRequest request) {
				
		BoardVO bvo = bsv.getDetail(bno);
		
		String path = request.getServletPath();
		log.info(">>>>>path > {}" , path);
		
		if(path.equals("/board/detail")) {
			int readCountOk = bsv.readCount(bno);
			bvo = bsv.getDetail(bno);
		}
		
		
		m.addAttribute("bvo",bvo);
	}
	
	@PostMapping("/update")
	public String update(BoardVO bvo) {
		
		int isOk = bsv.update(bvo);
		
		return "redirect:/board/detail?bno=" +bvo.getBno();
		
	}
	
	@GetMapping("/delete")
	public String delete(int bno) {
		
		int isOk= bsv.delete(bno);
		
		return "redirect:/board/list";
	}
	
	
	
	
	

}
