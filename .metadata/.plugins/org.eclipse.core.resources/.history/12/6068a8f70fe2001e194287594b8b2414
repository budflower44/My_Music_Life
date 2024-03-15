package com.mml.www.domain;

import java.util.List;

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
public class MemberVO {
	
	private String nickName;
	private String id;
	private String pwd;
	private String regAt;
	private String modAt;
	private List<AuthVO> authList;
	
	public MemberVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public MemberVO(String nickName, String id, String pwd) {
		this.nickName = nickName;
		this.id = id;
		this.pwd = pwd;
	}
	
	
	
}
