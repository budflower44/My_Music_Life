package com.mml.www.repository;

import java.util.List;

import com.mml.www.domain.AuthVO;
import com.mml.www.domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuths(String nickName);

	String getPwd(String id);

	MemberVO selectNickName(String username);

	List<AuthVO> selectAuths(String username);

	boolean updateLastLogin(String authNick);

}
