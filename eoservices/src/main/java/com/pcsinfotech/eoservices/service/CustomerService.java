package com.pcsinfotech.eoservices.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.pcsinfotech.eodata.entities.Country;
import com.pcsinfotech.eodata.repositories.CountryRepository;
import com.pcsinfotech.eodata.repositories.CustomerRepository;
import com.pcsinfotech.eodata.repositories.TimezoneRepository;
import com.pcsinfotech.eoservices.model.Customer;
import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCode;
import com.pcsinfotech.eoservices.model.TimeZone;
import com.pcsinfotech.eodata.entities.*;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private TimezoneRepository timezoneRepo;
	
	public Customer createCountryisoCode(Customer request){
		
		//Determine the Country Id using the ISO Code. 
		Country country = new Country();
		String isostrCode=request.getIsoCode();		
		System.out.println("iso code=="+isostrCode);
		List<Country> dbCountries = countryRepo.findCountriesByIsoCode(request.getIsoCode());
		
		if(!CollectionUtils.isEmpty(dbCountries)) {
			dbCountries.stream().forEach(t -> {
		    country.setId(t.getId());
		    request.setCountryId(country.getId());
		    country.setCountry(t.getCountry());
		    country.setIsoCode(t.getIsoCode());
			});
		} else {
			request.setError(ErrorCode.PCS_14);
			return request;
		}
		return request;
	}
		
	public Customer createtimeZone(Customer request) {
	
		//Determine the Time zone id for IST time zone. This is a hard coding for now.
		//This hard coding needs to be enhanced to get the right time zone from the request. 
		TimeZone timeZone1 = new TimeZone();
	
		List<com.pcsinfotech.eodata.entities.Timezone> dbTimezones = timezoneRepo.findTimezoneByAbbreviation("IST");
		if(!CollectionUtils.isEmpty(dbTimezones)) {
			dbTimezones.stream().forEach(t -> {
				timeZone1.setId(t.getId());
				request.setTimeZone(t.getId());
			});
			return request;
		} else {
			timeZone1.setError(ErrorCode.PCS_20);
			return request;
		}
	}
	
	public Customer create(Customer newCustomerRequest) {
	
		//Using the country id and mobile number find the active customer record. 
		List<com.pcsinfotech.eodata.entities.Customer> dbCustomers = 
				customerRepo.findbyCountryIdAndMobile(newCustomerRequest.getCountryId(), newCustomerRequest.getMobile());
		
		//Create the new customer record if one does not already exist other wise update the existing record
		com.pcsinfotech.eodata.entities.Customer newCustomer = new com.pcsinfotech.eodata.entities.Customer();
		
		if(!CollectionUtils.isEmpty(dbCustomers)) {
			dbCustomers.stream().forEach(t -> {
				newCustomer.setId(t.getId());	
			});
		}	
		newCustomer.setCountryId(newCustomerRequest.getCountryId());
		newCustomer.setFirstName(newCustomerRequest.getFirstName());
		newCustomer.setLastName(newCustomerRequest.getLastName());
		newCustomer.setMobile(newCustomerRequest.getMobile());
		newCustomer.setSoftDeleted(0);
		newCustomer.setTimeZoneId(newCustomerRequest.getTimeZone());  
		newCustomer.setVersionNumber(1);
		newCustomer.setCreatedDatetime(LocalDateTime.now());
		newCustomer.setUpdatedDatetime(LocalDateTime.now());;
		customerRepo.save(newCustomer);
		
		//TODO: Create or Update record in Events Table.
		
		//Build the Business Object from the created Customer Entity.
		Customer customer = new Customer();
		customer.setCountryId(newCustomer.getCountryId());
		customer.setCustomerId(newCustomer.getId());
		customer.setError(null);
		customer.setFirstName(newCustomer.getFirstName());
		customer.setLastName(newCustomer.getLastName());
		customer.setEventType(null); //TODO
		customer.setIsoCode(newCustomerRequest.getIsoCode()); 
		customer.setMobile(newCustomer.getMobile());
		return customer;
		
	}
	
	
	

	
}