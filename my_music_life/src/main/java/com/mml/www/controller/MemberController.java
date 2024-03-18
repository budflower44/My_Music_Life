package com.mml.www.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mml.www.domain.MemberVO;
import com.mml.www.security.CustomAuthMemberService;
import com.mml.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Slf4j(log.info()) 안먹힘...
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/**")
@Controller
public class MemberController {
	
	private final MemberService msv;
	
	private final BCryptPasswordEncoder bcEncoder;
		
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody String mvoJson) {		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// JSON 문자열을 Java 객체로 파싱
    	Map<String, Object> mvoMap;
		try {
			mvoMap = objectMapper.readValue(mvoJson, new TypeReference<Map<String, Object>>() {});
			
			System.out.println("@@@@@@@@@@@@@@@@@ join mvoMap >> "+mvoMap);
			
			String nickName = (String) mvoMap.get("nickName");
			String id = (String) mvoMap.get("id");
			String pwd = (String) mvoMap.get("pwd");
			
			MemberVO mvo = new MemberVO(id, bcEncoder.encode(pwd), nickName);
			
			int isOk = msv.memberInsert(mvo);
			
			return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
				new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//@postMappting("/login") 대신 CustomAuthMemberService 로 바로 타게됨
	
	//실패했을 때
	@PostMapping("/login")
	public ResponseEntity<String> loginPost(HttpServletRequest request, RedirectAttributes re){
		
		//로그인 실패 시 다시 로그인 유도할 수 있도록 로직 구현, 오류 메시지 전송
		//다시 로그인 유도
		re.addAttribute("id", request.getAttribute("id"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	
	// security 사용하지 않을 경우 login 방법으로 만들다가 중단하고 security로 넘어감.
	//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody String mvoJson) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		//JSON 문자열을 Java 객체로 파싱
//		Map<String, Object> mvoMap;
//		try {
//			mvoMap = objectMapper.readValue(mvoJson, new TypeReference<Map<String, Object>>() {});
//			
//			System.out.println("@@@@@@@@@@@@@@@@@ login mvo >> "+mvoMap);
//			
//			String id = (String) mvoMap.get("id");
//			String pwdVal = (String) mvoMap.get("pwd");
//			
//			String getPwd = msv.getPwd(id);
//			
//			Boolean pwdMathes = bcEncoder.matches(pwdVal, getPwd);
//			
//			if(pwdMathes) {
//				
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		int isOk = 0;
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
//			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
