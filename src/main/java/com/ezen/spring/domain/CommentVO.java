package com.ezen.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentVO {
	private long cno;
	private long bno;
	private String writer;
	private String content;
	private String regDate;
	
}
