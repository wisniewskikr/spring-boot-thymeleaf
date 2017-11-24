package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.InputCommand;
import pl.kwi.springboot.services.NameService;

@Controller
@RequestMapping(value="/input")
public class InputController {
	
	@Autowired
	private NameService nameService;

	@RequestMapping
	public String displayPage() {
		return "input";
	}
	
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public String handleButtonOk(@ModelAttribute("command")InputCommand command) {
		nameService.save(command.getName());
		return "redirect:/output";
	}

}