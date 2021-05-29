package org.camunda.bpm.getstarted.loanapproval.entitys;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.camunda.bpm.getstarted.loanapproval.model.User;

@Entity
@Table(name = "notifications")
public class Notifications {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Size(min=3, max = 250)
    private String message;
	
	@Size(min=3, max = 250)
    private String title;
    
	@ManyToOne
	@JoinColumn(nullable = true,name="users_id")
	private User user;


	private Date adddate;
	
	private boolean isSeen;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	public Notifications(Long id, @Size(min = 3, max = 250) String message, @Size(min = 3, max = 250) String title,
			User user, Date adddate, boolean isSeen) {
		super();
		this.id = id;
		this.message = message;
		this.title = title;
		this.user = user;
		this.adddate = adddate;
		this.isSeen = isSeen;
	}

	public Notifications() {
		super();
	}
	 
	 
	 
	
	
	
    
}
