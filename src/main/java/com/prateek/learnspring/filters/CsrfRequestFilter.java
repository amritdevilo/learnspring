package com.prateek.learnspring.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class CsrfRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (token != null) {
			Cookie cookie = new Cookie("XSRF-TOKEN", token.getToken());
			cookie.setPath("/");
			response.addCookie(cookie);
		} else {
			System.out.println("No csrf token found in the request" + request.getContextPath());
		}
		
		filterChain.doFilter(request, response);
	}
	
}
