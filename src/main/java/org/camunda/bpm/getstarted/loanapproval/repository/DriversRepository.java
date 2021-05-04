package org.camunda.bpm.getstarted.loanapproval.repository;


import org.camunda.bpm.getstarted.loanapproval.entitys.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepository extends JpaRepository<Drivers, Long> {

}
