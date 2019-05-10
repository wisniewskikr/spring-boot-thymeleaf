package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.kwi.springboot.commands.OutputCommand;
import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.db.repositories.UserRepository;
import pl.kwi.springboot.services.IdService;


@Controller
@RequestMapping(value="/output")
public class OutputController{
	
	
	@Autowired
	private IdService idService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping
	public String displayPage(@ModelAttribute("command")OutputCommand command){
		UserEntity userEntity = userRepository.findOne(Long.valueOf(idService.load()));
		command.setName(userEntity.getName());
		return "output";
	}
	
	@RequestMapping(value="/handle-button-back", method=RequestMethod.POST)
	public String handleButtonBack(){
		return "redirect:/input";
	}	

}
