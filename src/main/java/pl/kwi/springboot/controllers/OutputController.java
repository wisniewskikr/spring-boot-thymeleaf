package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.OutputCommand;
import pl.kwi.springboot.entities.UserEntity;
import pl.kwi.springboot.services.LdapService;
import pl.kwi.springboot.services.UidService;


@Controller
@RequestMapping(value="/output")
public class OutputController{
	
	
	@Autowired
	private UidService uidService;
	
	@Autowired
	private LdapService ldapService;
	
	@RequestMapping
	public String displayPage(@ModelAttribute("command")OutputCommand command){
		Long uid = Long.valueOf(uidService.load());
		UserEntity user = ldapService.load(uid);
		command.setName(user.getName());
		return "output";
	}
	
	@RequestMapping(value="/handle-button-back", method=RequestMethod.POST)
	public String handleButtonBack(){
		return "redirect:/input";
	}	

}
