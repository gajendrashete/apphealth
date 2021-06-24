package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/web")
public class AppWebController {

	@RequestMapping("/welcome")
	public ModelAndView appInfo() throws JsonProcessingException {
		return new ModelAndView("welcome", "message", "Simple Spring MVC App Landing");
	}

}
