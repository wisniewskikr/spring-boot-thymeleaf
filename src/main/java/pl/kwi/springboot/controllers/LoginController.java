package pl.kwi.springboot.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		return "login";
	}

}