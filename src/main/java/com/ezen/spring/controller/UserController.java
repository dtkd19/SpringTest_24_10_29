package com.ezen.spring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.spring.domain.UserVO;
import com.ezen.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RequiredArgsConstructor
@RequestMapping("/user/**")
@Slf4j
@Controller
public class UserController {
	private final UserService usv;
	private final BCryptPasswordEncoder bcEncoder;
	
	// mapping과 jsp의 경로가같다면 void 처리가능
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(UserVO uvo) {
		
		log.info(">>> register UserVO > {}", uvo);
		// encode : 암호화
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		
		
		int isOk = usv.register(uvo);
		return "redirect:/";	
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes re) {
		// 실제 로그인은 Security의 필터에서 가져감.
		// 로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지를 전송
		// 재 로그인을 유도
		log.info(">>> errMsg >> {}" , request.getAttribute("errMsg").toString());
		
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/user/login";
		
	}
	
	@GetMapping("/list")
	public void list(Model m) {	
		List<UserVO> userList = usv.getList();
		m.addAttribute("userList", userList);	
	}
	
	@GetMapping("/modify")
	public void modify() {}
	
	@PostMapping("/modify")
	public String modify(UserVO uvo, HttpServletRequest request, HttpServletResponse responce, RedirectAttributes re) {
		
		log.info(">>> modfiy uvo >> {}" ,uvo);
		
		int isOk = -1;
		
		if(uvo.getPwd().isEmpty() || uvo.getPwd().length() == 0) {
			// 비번 없이 업데이트 진행
			isOk = usv.modifyPwdEmpty(uvo);
		}else {
			// 비번 암호화 후 업데이트 진행
			uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
			isOk = usv.modify(uvo);			
		}		
		// 로그아웃 => index로 돌아가기
		logout(request, responce);
		if(isOk > 0 ) {
			re.addFlashAttribute("modify_msg", "ok");			
		} else {
			re.addFlashAttribute("modify_msg", "fail");						
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/user/delete")
	public String delete(HttpServletRequest request, HttpServletResponse responce, Principal principal, RedirectAttributes re) {
		log.info(principal.toString());
		
		String email = principal.getName();
		int isOk = usv.remove(email);
		
		if(isOk > 0 ) {
			re.addFlashAttribute("remove_msg", "ok");
		} else {
			re.addFlashAttribute("remove_msg", "fail");			
		}
		logout(request,responce);
		
		return "redirect:/";		
	}
	
	
		
	private void logout(HttpServletRequest request, HttpServletResponse responce) {
		// 내가 로그인 한 시큐리티의 authentication 객체
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		new SecurityContextLogoutHandler().logout(request, responce, authentication);
		
	}
	
		
	
}
