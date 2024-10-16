package com.pcsinfotech.eodata.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pcsinfotech.eodata.entities.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository <Customer, Long> {
	
	@Query("select u from Customer u where u.softDeleted = 0 and u.countryId=:countryId and u.mobile= :mobile")
	List<Customer> findbyCountryIdAndMobile(@Param("countryId") Long countryId,@Param("mobile") String mobile);
	
	
}
