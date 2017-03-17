package com.prateek.learnspring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prateek.learnspring.dao.SongsDao;
import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.ClientResponse;
import com.prateek.learnspring.model.LoginUser;
import com.prateek.learnspring.model.ServiceResponse;
import com.prateek.learnspring.model.Song;
import com.prateek.learnspring.model.User;
import com.prateek.learnspring.model.UserInfo;
import com.prateek.learnspring.model.SongAndRating;

@Controller
public class IndexController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/test")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Model model, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		/*
		 * @ModelAttribute find the form with modelAttribute as "user" and get that.
		 */
		System.out.println(user.toString());
		try{
			//Validations
			String message = user.getFirstName() + " Successfuly registered !";
			if (userDao.isEmailExists(user.getEmail())) {
				message = "Email Already exists !";
			} else {
				user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
				boolean isOk = userDao.addUser(user);
			}
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/login";
		} catch (Exception e) {
			return "redirect:/test";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(new LoginUser());
		return "login";
	}
	
	@RequestMapping(value={"/home", "/"}, method=RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			return "redirect:/login";
		}
		UserInfo userInfo = (UserInfo)userToken.getPrincipal();
		model.addAttribute("name", userInfo.getFirstName() + " " + userInfo.getLastName());
		model.addAttribute("flashMessage", userInfo.getFlashMessage());
		return "home";
	}

}
