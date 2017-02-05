package com.prateek.learnspring.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.model.User;

@Component
public class loginAuthProvider implements AuthenticationProvider{
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String email = authentication.getName().trim();
			String password = authentication.getCredentials().toString().trim();
			
			if (!userDao.isEmailExists(email)) {
				return null;
			}
			User user = userDao.getUserByEmail(email);
			System.out.println("Username : " + user.getEmail());
			if (user.getPassword().equals(password)) {
				List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
				auths.add(new SimpleGrantedAuthority("ROLE_USER"));
				/*
				 * UsernamePasswordAuthenticationToken(principal, creds, grantedAuthorityList);
				 */
//				new UsernamePasswordAuthenticationToken(user, user.getPassword(), auths).gev
				return new UsernamePasswordAuthenticationToken(user, user.getPassword(), auths);
			}
			
		} catch (AuthenticationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}