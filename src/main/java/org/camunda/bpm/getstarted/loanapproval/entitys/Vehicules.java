package org.camunda.bpm.getstarted.loanapproval.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "vehicules")
public class Vehicules {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    @Size(min=3, max = 50)
    private String note;
    
	@NotBlank
    @Size(min=3, max = 50)
    private String registrationPlate;
	
	
	@NotBlank
    @Size(min=3, max = 50)
    private String mark;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String model;
	
	@NotBlank
    @Size(min=3, max = 50)
    private String date;
	

    private Boolean isOut;
	
	

    
    
    

	public Boolean getIsOut() {
		return isOut;
	}

	public void setIsOut(Boolean isOut) {
		this.isOut = isOut;
	}

	public Vehicules(Long id, @NotBlank @Size(min = 3, max = 50) String note,
			@NotBlank @Size(min = 3, max = 50) String registrationPlate, @NotBlank @Size(min = 3, max = 50) String mark,
			@NotBlank @Size(min = 3, max = 50) String model, @NotBlank @Size(min = 3, max = 50) String date) {
		super();
		this.id = id;
		this.note = note;
		this.registrationPlate = registrationPlate;
		this.mark = mark;
		this.model = model;
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}


	public Vehicules() {
		super();
	}
	
	
	
}
