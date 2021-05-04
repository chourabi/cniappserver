package org.camunda.bpm.getstarted.loanapproval.entitys;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.camunda.bpm.getstarted.loanapproval.model.User;





@Entity
@Table(name = "vehicules_request")
public class VehiculeRequest {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String startLocation;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String arrivalLocation;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String startDate;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String arrivalDate;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String startTime;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String arrivalTime;
	
    private String cargo;
	
	@NotBlank
    @Size(min=3, max = 250)
    private String reason;
	
	


    private String cancelReason;
	

    private int status;
	

	
	
	@ManyToOne
	@JoinColumn(name="vehicules_id")
	private Vehicules vehicule;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private User employee;
	
	
	
	
	
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}



	public Vehicules getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicules vehicule) {
		this.vehicule = vehicule;
	}
	
	

	public VehiculeRequest(Long id, @NotBlank @Size(min = 3, max = 50) String startLocation,
			@NotBlank @Size(min = 3, max = 50) String arrivalLocation,
			@NotBlank @Size(min = 3, max = 50) String startDate, @NotBlank @Size(min = 3, max = 50) String arrivalDate,
			@NotBlank @Size(min = 3, max = 50) String startTime, @NotBlank @Size(min = 3, max = 50) String arrivalTime,
			@NotBlank @Size(min = 3, max = 250) String cargo, @NotBlank @Size(min = 3, max = 250) String reason,
			 Vehicules vehicule) {
		super();
		this.id = id;
		this.startLocation = startLocation;
		this.arrivalLocation = arrivalLocation;
		this.startDate = startDate;
		this.arrivalDate = arrivalDate;
		this.startTime = startTime;
		this.arrivalTime = arrivalTime;
		this.cargo = cargo;
		this.reason = reason;
		this.vehicule = vehicule;
	}

	public VehiculeRequest() {
		super();
	}
	
	
	
	
	
	
}
