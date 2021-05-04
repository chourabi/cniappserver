package org.camunda.bpm.getstarted.loanapproval.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "drivers")
public class Drivers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    @Size(min=3, max = 50)
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drivers(Long id, @NotBlank @Size(min = 3, max = 50) String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Drivers() {
		super();
	}
	
	
	
}
