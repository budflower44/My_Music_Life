package com.mml.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mml.www.domain.CommentDTO;
import com.mml.www.domain.CommentVO;
import com.mml.www.domain.TracksVO;
import com.mml.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentDAO cdao;

	@Override
	public int postComment(CommentVO cvo) {
		return cdao.insertComment(cvo);
	}

	@Override
	public List<CommentVO> getComment(String id) {
			List<CommentVO> cmtList = cdao.getComment(id);
		
		return cmtList;
	}
	
}
