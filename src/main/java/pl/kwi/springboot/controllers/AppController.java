package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AppController {

	@RequestMapping(value="/")
	public String index() {
		return "redirect:input";
	}
	
	@RequestMapping(value="/input")
	public String input() {
		return "input";
	}
	
	@RequestMapping(value="/output")
	public String output() {
		return "output";
	}
	
}