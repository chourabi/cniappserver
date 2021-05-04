package org.camunda.bpm.getstarted.loanapproval.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hellotest")

public class HelloWorldControler {
	
	
	
	@GetMapping("/hello")
	public String helloworld() {
		
		return "hello world";
	}

}
