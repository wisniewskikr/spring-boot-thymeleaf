package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.InputCommand;
import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.db.repositories.UserRepository;
import pl.kwi.springboot.services.IdService;

@Controller
@RequestMapping(value="/input")
public class InputController {
	
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private UserRepository userRepository;
	

	@RequestMapping
	public String displayPage() {
		return "input";
	}
	
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public String handleButtonOk(@ModelAttribute("command")InputCommand command) {
		UserEntity userEntity = userRepository.save(new UserEntity(command.getName()));
		idService.save(String.valueOf(userEntity.getId()));
		return "redirect:/output";
	}

}