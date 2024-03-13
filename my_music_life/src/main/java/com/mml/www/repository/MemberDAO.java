package com.mml.www.repository;

import com.mml.www.domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuths(String nickName);

}
