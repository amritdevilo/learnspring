package com.prateek.learnspring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.model.ClientResponse;
import com.prateek.learnspring.model.LoginUser;
import com.prateek.learnspring.model.ServiceResponse;
import com.prateek.learnspring.model.User;
import com.prateek.learnspring.model.UserInfo;

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
		
		//Validations
		String message = user.getFirstName() + " Successfuly registered !";
		if (userDao.isEmailExists(user.getEmail())) {
			message = "Email Already exists !";
		} else {
			boolean isOk = userDao.addUser(user);
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/test";
	}
	
	/*
	 * Creating a single repository for toasts, will it interfere with other requests
	 */
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(new LoginUser());
		return "login";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		UserInfo userInfo = (UserInfo)userToken.getPrincipal();
		model.addAttribute("name", userInfo.getFirstName() + " " + userInfo.getLastName());
//		model.addAttribute("userInfo", userInfo);
		model.addAttribute("flashMessage", userInfo.getFlashMessage());
		return "home";
	}
	
	@RequestMapping(value="/client", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ClientResponse> getCurrentUserInfo(HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new ClientResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), null));
		}
		((UserInfo)(userToken.getPrincipal())).setFlashMessage("New Message "+ System.currentTimeMillis());
		UserInfo userInfo = (UserInfo)userToken.getPrincipal();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ClientResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userInfo));
	}
}
