package org.camunda.bpm.getstarted.loanapproval.controller;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.camunda.bpm.getstarted.loanapproval.repository.VehiculesRepository;
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
@RequestMapping("/api/vehicules")
public class VehiculesController {
	
	@Autowired
	VehiculesRepository vehiculesRepository;
	
	
	@GetMapping("/list")
	public List<Vehicules> getALLAchats(){
		return this.vehiculesRepository.findAll();
	}
	
	 @PostMapping("/add")
	 public Vehicules modiferProduit(@RequestBody Vehicules vm ){
		 vm.setIsOut(false);
		 return this.vehiculesRepository.save(vm);
		 
	 }
	 
	 @GetMapping("/delete/{id}")
	 public void deleteProduit(@PathVariable(value ="id") Long id){
		 this.vehiculesRepository.delete(   this.vehiculesRepository.findById(id).get()  );
	 }
	 
	 @GetMapping("/details/{id}")
	 public Vehicules vehiculesDetails(@PathVariable(value ="id") Long id){
		return  this.vehiculesRepository.findById(id).get()  ;
	 }
	 
	 
	 @PostMapping("/update/{id}")
	 public void updateVehicule(@PathVariable(value ="id") Long id,@RequestBody Vehicules vm){
		 Vehicules v = this.vehiculesRepository.findById(id).get();
		 v.setDate(vm.getDate());
		 v.setMark(vm.getMark());
		 v.setNote(vm.getNote());
		 v.setRegistrationPlate(vm.getRegistrationPlate());
		 v.setIsOut(vm.getIsOut());
		 
		 
		 
		 
		 this.vehiculesRepository.save(   v  );
	 }
	 


}
