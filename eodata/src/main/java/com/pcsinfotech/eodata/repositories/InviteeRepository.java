package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcsinfotech.eodata.entities.Invitee;

public interface InviteeRepository extends JpaRepository<Invitee, Long> {

	@Query("select u from Invitee u where u.softDeleted = 0 and u.customerId = :customerId")
	List<Invitee> findInviteeByCustomerId(@Param("customerId") Long customerId);

}
