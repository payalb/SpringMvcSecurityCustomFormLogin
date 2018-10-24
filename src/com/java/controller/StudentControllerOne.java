package com.java.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentControllerOne {
	@RequestMapping("/add")
	public String doWork(Authentication auth){
		/*ModelAndView mv = new ModelAndView("addStudent");
		System.out.println(auth.getAuthorities());
*/		return "redirect:/addStudent";
	}

}
