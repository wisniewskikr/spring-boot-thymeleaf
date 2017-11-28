package pl.kwi.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pl.kwi.springboot.db.entities.UserEntity;
import pl.kwi.springboot.services.NameService;

@RestController
@RequestMapping("/user")
@Api(value="user", description="User operations")
public class UserController {
	
	
	@Autowired
	private NameService nameService;
	
	private static final Gson gson = new Gson();

	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add an user")	
    public ResponseEntity<String> addUser(@RequestBody @ApiParam(name = "User Entity", value = "User to be added") UserEntity user){
		nameService.save(user.getName());
        return new ResponseEntity<String>(gson.toJson("User saved successfully"), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/show", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Show an user",response = UserEntity.class)    
    public ResponseEntity<UserEntity> showUser(){
	 	return new ResponseEntity<UserEntity>(new UserEntity(nameService.load()), HttpStatus.OK);
    }
	

}
