package pl.kwi.springboot.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.OutputCommand;
import pl.kwi.springboot.services.NameService;
import pl.kwi.springboot.socialmedia.util.SocialControllerUtil;


@Controller
@RequestMapping(value="/output")
public class OutputController{
	
	
	@Autowired
	private NameService nameService;
	
	@Autowired
    private SocialControllerUtil util;
	
	
	@RequestMapping
	public String displayPage(@ModelAttribute("command")OutputCommand command, HttpServletRequest request, Principal currentUser, Model model){
		util.setModel(request, currentUser, model);
		command.setName(nameService.load());
		return "output";
	}
	
	@RequestMapping(value="/handle-button-back", method=RequestMethod.POST)
	public String handleButtonBack(){
		return "redirect:/input";
	}	

}
