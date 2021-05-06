package org.camunda.bpm.getstarted.loanapproval.message.request;

public class RefuseModel {
	private String reason;
	private Long id;
	
	

	public RefuseModel() {
		super();
	}

	public RefuseModel(String reason, Long id) {
		super();
		this.reason = reason;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
