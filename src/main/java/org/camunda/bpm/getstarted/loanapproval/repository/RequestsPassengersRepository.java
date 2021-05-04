package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.entitys.RequestsPassengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsPassengersRepository extends JpaRepository<RequestsPassengers, Long>{

}
