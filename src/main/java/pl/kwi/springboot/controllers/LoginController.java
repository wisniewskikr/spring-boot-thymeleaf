package pl.kwi.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(@RequestParam(required = false) String errorCode, Model model) {
		
		if ("BAD_CREDENTIALS".equals(errorCode)) {
			model.addAttribute("errorMessage", "Invalid username or password.");
		} else if ("LOCKED".equals(errorCode)) {
			model.addAttribute("errorMessage", "This account has been blocked");
		}
		
		return "login";
		
	}

}