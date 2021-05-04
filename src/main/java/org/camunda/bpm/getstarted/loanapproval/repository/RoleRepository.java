package org.camunda.bpm.getstarted.loanapproval.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.camunda.bpm.getstarted.loanapproval.model.Role;
import org.camunda.bpm.getstarted.loanapproval.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}