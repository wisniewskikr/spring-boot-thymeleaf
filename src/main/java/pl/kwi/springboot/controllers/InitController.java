package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	
	@RequestMapping(value="/")
	public String init() {
		return "redirect:input";
	}	

}