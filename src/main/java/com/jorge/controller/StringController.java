package com.jorge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StringController {
	
	@RequestMapping(value="/concat", method=RequestMethod.GET)
	public String concat(@RequestParam String a, @RequestParam String b, Model model) {
		String result = a + b;
		
		model.addAttribute("result", result);
		
		return "concat";
	}
}
