package com.mml.www.repository;

import java.util.List;

import com.mml.www.domain.CommentDTO;
import com.mml.www.domain.CommentVO;

public interface CommentDAO {

	int insertComment(CommentVO cvo);

	List<CommentVO>getComment(String id);

}
