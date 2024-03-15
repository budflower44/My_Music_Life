package com.mml.www.service;

import com.mml.www.domain.MemberVO;

public interface MemberService {

	int memberInsert(MemberVO mvo);

	String getPwd(String id);

	boolean updateLastLogin(String id);

}
