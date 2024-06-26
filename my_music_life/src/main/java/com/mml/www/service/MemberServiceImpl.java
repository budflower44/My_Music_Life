package com.mml.www.service;

import org.springframework.stereotype.Service;

import com.mml.www.domain.MemberVO;
import com.mml.www.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO mdao;

	@Override
	public int memberInsert(MemberVO mvo) {
		System.out.println("@@@@@@@@@@@@@@@@ member insert mvo >> "+mvo);
		int isOk = mdao.insert(mvo);
		System.out.println(">>>>>>>>>>>>>>>>>>>>> insert DAO is"+(isOk>0?"OK":"Fail"));
		return mdao.insertAuths(mvo.getId());
	}

	@Override
	public String getPwd(String id) {
		return mdao.getPwd(id);
	}

	@Override
	public boolean updateLastLogin(String authId) {
		return mdao.updateLastLogin(authId);
	}
	
}
