package com.prateek.learnspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.model.User;

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
		userDao.addUser(user);
		redirectAttributes.addFlashAttribute("message", user.getFirstName() + " Successfuly registered !");
		return "redirect:/test";
	}
}
