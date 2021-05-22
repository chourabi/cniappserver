package org.camunda.bpm.getstarted.loanapproval.controller;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.Drivers;
import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.camunda.bpm.getstarted.loanapproval.model.User;
import org.camunda.bpm.getstarted.loanapproval.repository.DriversRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/drivers")
public class DriversController {
	
	@Autowired
	DriversRepository driversRepository;
	
	@GetMapping("/list")
	public List<Drivers> getDriversList(){
		return this.driversRepository.findAll();
	}
	
	
	 @PostMapping("/add")
	 public Drivers addNewDriver(@RequestBody Drivers vm ){
		 return this.driversRepository.save(vm);
		 
	 }

}
