package org.camunda.bpm.getstarted.loanapproval.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.camunda.bpm.getstarted.loanapproval.model.User;

@Entity
@Table(name = "passengers_requests")
public class RequestsPassengers {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private User passenger;
	
	@ManyToOne
	@JoinColumn(name="vehicules_request_id")
	private VehiculeRequest request;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getPassenger() {
		return passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	public VehiculeRequest getRequest() {
		return request;
	}

	public void setRequest(VehiculeRequest request) {
		this.request = request;
	}

	public RequestsPassengers(Long id, User passenger, VehiculeRequest request) {
		super();
		this.id = id;
		this.passenger = passenger;
		this.request = request;
	}

	public RequestsPassengers() {
		super();
	}
	
	
	
	

}
