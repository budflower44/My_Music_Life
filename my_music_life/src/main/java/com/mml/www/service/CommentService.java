package com.mml.www.service;

import java.util.List;
import java.util.Map;

import com.mml.www.domain.CommentVO;

public interface CommentService {

	int postComment(CommentVO cvo);

	List<CommentVO> getComment(String id);

}
