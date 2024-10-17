package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pcsinfotech.eodata.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Cacheable("IsoCodes")
	@Query("select u from Country u where u.softDeleted = 0")
	List<Country> getIsoCodes();

	@Query("select u from Country u where u.softDeleted = 0")
	List<Country> getCountries();

	@Query("select u from Country u where u.softDeleted = 0 and u.country = :country and u.isoCode = :isoCode")
	List<Country> findCountriesByCountryAndIsoCode(@Param("country") String country, @Param("isoCode") String isoCode);

	@Query("select u from Country u where u.softDeleted = 0 and u.isoCode = :isoCode")
	List<Country> findCountriesByIsoCode(@Param("isoCode") String isoCode);

}
