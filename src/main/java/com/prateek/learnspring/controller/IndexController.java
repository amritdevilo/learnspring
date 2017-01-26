package com.prateek.learnspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/bc/mc/fc")
public class IndexController {
	
	@RequestMapping("/test")
	public String index() {
		return "index";
	}
}
