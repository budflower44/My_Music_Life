package com.mml.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CommentVO {
	
	private long cno;
	private String id;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
		
}
