package org.camunda.bpm.getstarted.loanapproval.message.request;

import java.util.List;


import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;

public class VehiculeRequestModel {
    private String start_location;
    private String arrival_location;
    private String start_date;
    private String arrival_date;
    private String start_time;
    private String arrival_time;
    private String cargo;
    private String resaon;
    private int status;
	private List<Long> passengers;
	private Long vehicule_id;
	private Long employee_id;
	public String getStart_location() {
		return start_location;
	}
	public void setStart_location(String start_location) {
		this.start_location = start_location;
	}
	public String getArrival_location() {
		return arrival_location;
	}
	public void setArrival_location(String arrival_location) {
		this.arrival_location = arrival_location;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getResaon() {
		return resaon;
	}
	public void setResaon(String resaon) {
		this.resaon = resaon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Long> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Long> passengers) {
		this.passengers = passengers;
	}
	public Long getVehicule_id() {
		return vehicule_id;
	}
	public void setVehicule_id(Long vehicule_id) {
		this.vehicule_id = vehicule_id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	
	

	
	
	
	
	
	
}
