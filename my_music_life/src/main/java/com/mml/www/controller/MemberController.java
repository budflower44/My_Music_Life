package com.mml.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mml.www.domain.MemberVO;
import com.mml.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/**")
@Controller
public class MemberController {
	
	private final MemberService msv;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(MemberVO mvo) {
		System.out.println("@@@@@@@@@@@@@@@@@ login mvo >> "+mvo);
		int isOk = msv.memberInsert(mvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberVO mvo) {
		System.out.println("@@@@@@@@@@@@@@@@@ join mvo >> "+mvo);
		int isOk = msv.memberInsert(mvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
