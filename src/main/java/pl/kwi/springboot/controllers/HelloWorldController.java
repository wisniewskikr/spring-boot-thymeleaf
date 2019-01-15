package pl.kwi.springboot.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
			
	@RequestMapping(value = "/api/hello")
	public void initHello(HttpServletResponse response) throws IOException {
		response.sendRedirect("hello/Unknown");
	}
	
	@RequestMapping(value = "/api/hello/{name}")
	public String sayHelloWorld(@PathVariable("name") String name) {
		name = (name == null) ? "Unknown" : name;
		return "Hello World " + name;
	}

}