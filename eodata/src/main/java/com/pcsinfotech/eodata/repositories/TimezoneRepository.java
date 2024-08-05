package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pcsinfotech.eodata.entities.Timezone;

@Repository
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {
	
	@Query("select u from Timezone u where u.softDeleted = 0 and u.abbreviation = :abbreviation")
    List<Timezone> findTimezoneByAbbreviation(@Param("abbreviation") String abbreviation);
	
}
