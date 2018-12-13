package pl.kwi.springboot.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kwi.springboot.commands.LoginCommand;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(
			@ModelAttribute("command")LoginCommand command,
			HttpServletRequest request) {
		
		handleRememberUsername(command, request);		
		return "login";
		
	}
	
	private void handleRememberUsername(LoginCommand command, HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("username".equals(cookie.getName())) {
				command.setUsername(cookie.getValue());
				command.setRememberUsername(true);
			}
		}
		
	}

}