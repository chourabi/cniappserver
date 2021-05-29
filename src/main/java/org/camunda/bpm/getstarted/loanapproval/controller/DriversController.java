package org.camunda.bpm.getstarted.loanapproval.controller;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.Drivers;
import org.camunda.bpm.getstarted.loanapproval.entitys.VehiculeRequest;
import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.camunda.bpm.getstarted.loanapproval.model.User;
import org.camunda.bpm.getstarted.loanapproval.repository.DriversRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.UserRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.VehiculeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	VehiculeRequestRepository vehiculeRequestRepository;
	
	@GetMapping("/list")
	public List<Drivers> getDriversList(){
		return this.driversRepository.findAll();
	}
	
	 @GetMapping("/details/{id}")
	 public Drivers vehiculesDetails(@PathVariable(value ="id") Long id){
		return  this.driversRepository.findById(id).get()  ;
	 }
	 
	 @GetMapping("/delete/{id}")
	 public void deleteDriver(@PathVariable(value ="id") Long id){
		 Drivers x =   this.driversRepository.findById(id).get()  ;
		 
		 List<VehiculeRequest> tmp = this.vehiculeRequestRepository.findAll();
		 
		 for(VehiculeRequest t:tmp) {
			 if( t.getDriver() != null ) {
				 if( t.getDriver().getId() == x.getId() ) {
					 this.vehiculeRequestRepository.delete(t);
				 }
			 }
		 }
		 
		 
		 
		 this.driversRepository.delete(x);
	 }
	 
	 
	 @PostMapping("/update/{id}")
	 public void updateVehicule(@PathVariable(value ="id") Long id,@RequestBody Drivers vm){
		 vm.setId(id);
		 
		 Drivers d = vm;
		 
		 
		 
		 
		 
		 this.driversRepository.save(   d  );
	 }
	 
	
	
	 @PostMapping("/add")
	 public Drivers addNewDriver(@RequestBody Drivers vm ){
		 return this.driversRepository.save(vm);
		 
	 }

}
