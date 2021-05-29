package org.camunda.bpm.getstarted.loanapproval.controller;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.camunda.bpm.getstarted.loanapproval.model.ResponseJson;
import org.camunda.bpm.getstarted.loanapproval.model.User;
import org.camunda.bpm.getstarted.loanapproval.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/list")
	public List<User> getALLAchats(){
		return this.userRepository.findAll();
	}
	
	
	
	
	 @GetMapping("/delete/{id}")
	 public void deleteEmployee(@PathVariable(value ="id") Long id){
		
			 
			 User x = this.userRepository.findById(id).get();
			 
			 x.setRoles(null);
			 this.userRepository.save(x);
			 
			 
			 this.userRepository.delete(   this.userRepository.findById(id).get()  );
			
	 }
}


