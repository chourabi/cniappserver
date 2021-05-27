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
	 public ResponseJson deleteEmployee(@PathVariable(value ="id") Long id){
		 ResponseJson rj = new ResponseJson();
		 try {
			 this.userRepository.delete(   this.userRepository.findById(id).get()  );
			 rj.setSuccess(true);
			 rj.setMessage("Employee '"+this.userRepository.findById(id).get().getName()+"' deleted successfully.");
			 return rj;
			 
		 }catch(Exception e) {
			 rj.setSuccess(false);
			 rj.setMessage("sorry but these employee cannot be deleted. ");
			 return rj;
		 }
	 }
}


