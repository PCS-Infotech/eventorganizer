package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcsinfotech.eodata.entities.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	@Query("select u from Organizer u where u.softDeleted = 0 and u.customerId = :customerId")
	List<Organizer> findOrganizerByCustomerId(@Param("customerId") Long customerId);

}
