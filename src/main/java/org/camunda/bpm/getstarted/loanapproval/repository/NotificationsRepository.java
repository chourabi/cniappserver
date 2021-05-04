package org.camunda.bpm.getstarted.loanapproval.repository;

import java.util.List;

import org.camunda.bpm.getstarted.loanapproval.entitys.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationsRepository extends JpaRepository<Notifications, Long>{


}
