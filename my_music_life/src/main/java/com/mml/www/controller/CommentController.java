package com.mml.www.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mml.www.domain.CommentVO;
import com.mml.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/**")
@Controller
public class CommentController {
	
	private final CommentService csv;
	
	@PostMapping("/post")
	public ResponseEntity<String> postComment(@RequestBody CommentVO cvo) {
		log.info("@@@@@@@@@@@@@@@@@ cvo >> "+cvo);
		int isOk = csv.postComment(cvo);
		return (isOk>0) ? new ResponseEntity<String> ("1", HttpStatus.OK) : 
			new ResponseEntity<String> ("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/getComment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentVO>> getComment(@RequestBody String id){
		System.out.println("@@@@@@@@@@@@@@@ id >> "+id);
		
		try {
			List<CommentVO> cmtList = csv.getComment(id);
			System.out.println(cmtList);
			
			return new ResponseEntity<List<CommentVO>>(cmtList, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("getDetailInfoTrack JSON 파싱 오류");
			List<CommentVO> listNull = null;
			return new ResponseEntity<List<CommentVO>>(listNull, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}