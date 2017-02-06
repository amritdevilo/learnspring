package com.prateek.learnspring.providers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.model.User;
import com.prateek.learnspring.model.UserInfo;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
	
	private UserDao userDao;
	private int sessionTimeOut;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		try{
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (user != null) {
					request.getSession().setMaxInactiveInterval(sessionTimeOut);
					redirectStrategy.sendRedirect(request, response, "/home");
				}
			} else {
				System.out.println("Anon is here");
				redirectStrategy.sendRedirect(request, response, "/login");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
