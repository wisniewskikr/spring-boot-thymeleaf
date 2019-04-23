package pl.kwi.springboot.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.InputCommand;
import pl.kwi.springboot.services.NameService;
import pl.kwi.springboot.socialmedia.util.SocialControllerUtil;

@Controller
@RequestMapping(value="/input")
public class InputController {
	
	@Autowired
	private NameService nameService;
	
	@Autowired
    private SocialControllerUtil util;

	@RequestMapping
	public String displayPage(HttpServletRequest request, Principal currentUser, Model model) {
		util.setModel(request, currentUser, model);
		return "input";
	}
	
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public String handleButtonOk(@ModelAttribute("command")InputCommand command) {
		nameService.save(command.getName());
		return "redirect:/output";
	}

}