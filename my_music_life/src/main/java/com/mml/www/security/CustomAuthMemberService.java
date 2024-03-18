package com.mml.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mml.www.domain.MemberVO;
import com.mml.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username이 DB에 설정되어 있는 id이 맞는지 체크
		// 인증하여 해당 객체를 AuthMember로 리턴
		System.out.println("@@@@@@@@ username >>"+username);
		MemberVO mvo = mdao.selectId(username);
		System.out.println(mvo);
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		System.out.println(mvo);
		return new AuthMember(mvo);
	}

}
