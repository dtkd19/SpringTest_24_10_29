package com.ezen.spring.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ezen.spring.domain.UserVO;

import lombok.Getter;


@Getter
public class AuthUser extends User{
	
	private static final long serialVersionUID = 1L;
	private UserVO uvo; 

	public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	public AuthUser(UserVO uvo) {
		
		super(uvo.getEmail(), uvo.getPwd(),
				uvo.getAuthList().stream()
				.map(AuthVO -> new SimpleGrantedAuthority(AuthVO.getAuth()))
				.collect(Collectors.toList())
				);
		
		this.uvo = uvo;
	}
	

}
