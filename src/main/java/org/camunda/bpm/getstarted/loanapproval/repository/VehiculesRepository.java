package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculesRepository extends JpaRepository<Vehicules, Long> {

}
