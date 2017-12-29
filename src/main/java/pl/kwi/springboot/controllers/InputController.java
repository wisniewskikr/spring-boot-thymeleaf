package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.InputCommand;
import pl.kwi.springboot.entities.UserEntity;
import pl.kwi.springboot.services.LdapService;
import pl.kwi.springboot.services.UidService;

@Controller
@RequestMapping(value="/input")
public class InputController {
	
	@Autowired
	private UidService uidService;
	
	@Autowired
	private LdapService ldapService;

	@RequestMapping
	public String displayPage() {		
		return "input";
	}
	
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public String handleButtonOk(@ModelAttribute("command")InputCommand command) {
		String uid = ldapService.generateUid();
		ldapService.save(new UserEntity(uid, command.getName()));
		uidService.save(uid);
		return "redirect:/output";
	}

}