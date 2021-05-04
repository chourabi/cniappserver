package org.camunda.bpm.getstarted.loanapproval.message.response;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.RequestsPassengers;
import org.camunda.bpm.getstarted.loanapproval.entitys.VehiculeRequest;
import org.camunda.bpm.getstarted.loanapproval.model.User;

public class RequestVehiculeItem {

	private VehiculeRequest request;
	private List<User> passengers;



	public VehiculeRequest getRequest() {
		return request;
	}



	public void setRequest(VehiculeRequest request) {
		this.request = request;
	}



	public List<User> getPassengers() {
		return passengers;
	}



	public void setPassengers(List<User> passengers) {
		this.passengers = passengers;
	}

	


	public RequestVehiculeItem(VehiculeRequest request, List<User> passengers) {
		super();
		this.request = request;
		this.passengers = passengers;
	}



	public RequestVehiculeItem() {
		super();
	}
	
	
	
}
