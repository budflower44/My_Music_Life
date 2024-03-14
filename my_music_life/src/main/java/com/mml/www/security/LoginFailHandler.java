package com.mml.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
	
	private String authNick;
	private String errorMessage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		setAuthNick(request.getParameter("nickName"));
		
		//exception 발생 시 에러 메시지 저장
		if(exception instanceof BadCredentialsException ||
				exception instanceof InternalAuthenticationServiceException) {
			setErrorMessage(exception.getMessage().toString());
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@ errMsg >> "+errorMessage);
		
		request.setAttribute("nickName", authNick);
		request.setAttribute("errMsg", errorMessage);
		request.getRequestDispatcher("/member/login?error").forward(request, response);
	}

}
