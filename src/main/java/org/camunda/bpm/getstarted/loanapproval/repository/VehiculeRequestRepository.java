package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.entitys.VehiculeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRequestRepository  extends JpaRepository<VehiculeRequest, Long> {

}
