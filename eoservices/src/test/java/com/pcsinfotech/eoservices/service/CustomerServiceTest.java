package com.pcsinfotech.eoservices.service;

//import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pcsinfotech.eodata.entities.Country;
import com.pcsinfotech.eodata.entities.Timezone;
import com.pcsinfotech.eodata.repositories.CountryRepository;
import com.pcsinfotech.eodata.repositories.CustomerRepository;
import com.pcsinfotech.eodata.repositories.TimezoneRepository;
import com.pcsinfotech.eoservices.model.Customer;
import com.pcsinfotech.eoservices.model.ErrorCode;
//import com.pcsinfotech.eodata.entities.Customer;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepo;

	@Mock
	private CountryRepository countryRepo;
	
	@Mock
	private TimezoneRepository timezoneRepo;
	
	@InjectMocks
	private CustomerService customerService;
	
	

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
       // com.pcsinfotech.eoservices.model.Customer request;
       
    }
	
	@Test
	void createtest_CountryIsoCode_Success() {
		
		    Customer request = new  com.pcsinfotech.eoservices.model.Customer();
		    request.setFirstName("Vanitha");
	        request.setLastName("Gururajan");
	        request.setMobile("9876543905");
	        request.setIsoCode("+91");
	        request.setEventType("Wedding");
		
	        Country mockCountry = new Country();
	        mockCountry.setId(1l);
	        mockCountry.setCountry("India");
	        mockCountry.setIsoCode("+91");

	        ArrayList<Country> countries = new ArrayList<>();
	        countries.add(mockCountry);
	      
	        Mockito.lenient().when(countryRepo.findCountriesByIsoCode(request.getIsoCode())).thenReturn(countries);
	        // Act
	        Customer result = customerService.createCountryisoCode(request);
	        // Assert
	        assertEquals(1, result.getCountryId().intValue());
	        assertEquals("+91", result.getIsoCode());
      
}
	
	 @Test
	 public void testCreateCounteryisoCode_NoCountryFound() {
	        // Arrange
	        Customer request = new Customer();
	        
	        request.setFirstName("Vanitha");
	        request.setLastName("Gururajan");
	        request.setMobile("9876543905");
	        request.setIsoCode("abc");
	        request.setEventType("Wedding");
	        request.setError(ErrorCode.PCS_14);
	       
	        // Create an empty ArrayList
	        Country mockCountry = new Country();
	        mockCountry.setId(1l);
	        mockCountry.setCountry("India");
	        mockCountry.setIsoCode("abc");
	        ArrayList<Country> countries = new ArrayList<>();
	        countries.add(mockCountry);
	        
	        // Mock the repository method to return the empty ArrayList
	        Mockito.lenient().when(countryRepo.findCountriesByIsoCode(request.getIsoCode())).thenReturn(new ArrayList<>());

	        // Act
	        Customer result = customerService.createCountryisoCode(request);
	        
	        // Assert
	        assertEquals(ErrorCode.PCS_14, result.getError()); // Adjust according to your error handling
	 }

	 @Test	
	 public void  createTimeZone(){
		 
		    Customer request = new  com.pcsinfotech.eoservices.model.Customer();
	        request.setFirstName("Vanitha");
	        request.setLastName("Gururajan");
	        request.setMobile("9876543905");
	        request.setIsoCode("+91");
	        request.setEventType("Wedding");	 
		    request.setCountryId(1l);
		     
		    com.pcsinfotech.eodata.entities.Timezone mockTimezone = new com.pcsinfotech.eodata.entities.Timezone();
	        mockTimezone.setId(11l);
	        mockTimezone.setAbbreviation("IST");
	        
	        ArrayList<Timezone> timezones = new ArrayList<>();
	        timezones.add(mockTimezone);
	        Mockito.lenient().when(timezoneRepo.findTimezoneByAbbreviation("IST")).thenReturn(timezones);
	        Customer result = customerService.createtimeZone(request);
	        
	        // Assert
	        assertEquals(11l, result.getTimeZone());
	        assertEquals("+91", result.getIsoCode());	       
  }
	
	 @Test
	 public void  createTimeZone_NotFound()
	  {
		 Customer request = new Customer();
	        request.setFirstName("Vanitha");
	        request.setLastName("Gururajan");
	        request.setMobile("9876543905");
	        request.setIsoCode("+91");
	        request.setEventType("Wedding");
	        request.setTimeZone(11l);
	        request.setError(ErrorCode.PCS_20);
		 
	        Timezone timeZone = new Timezone();
	        timeZone.setId(1l);
	        timeZone.setAbbreviation("GST");
	        
	        ArrayList<Timezone> timezon = new ArrayList<>();
	        timezon.add(timeZone);

	        Mockito.lenient().when(timezoneRepo.findTimezoneByAbbreviation("GST")).thenReturn(new ArrayList<>());

	        // Act
	        Customer result = customerService.createtimeZone(request);
	        System.out.println("request output is ===getError==="+result.getError());
	        // Assert
	        assertEquals(ErrorCode.PCS_20, result.getError()); // Adjust according to your error handling
	       
	  }
	 
	 @Test
	 public void testCreate_NewCustomer() {	
		    Customer request = new Customer();
	        request.setFirstName("Vanitha");
	        request.setLastName("Gururajan");
	        request.setMobile("9876543905");
	        request.setIsoCode("+91");
	        request.setEventType("Wedding");
	        request.setTimeZone(11l);
	        request.setCountryId(1l);
	       
	        com.pcsinfotech.eodata.entities.Customer response = new  com.pcsinfotech.eodata.entities.Customer();
	        
	        response.setCountryId(request.getCountryId());
	        response.setFirstName(request.getFirstName());
	        response.setLastName(request.getLastName());
	        response.setMobile(request.getMobile());
	        response.setTimeZoneId(request.getTimeZone());
	        response.setVersionNumber(1);
	        response.setCreatedDatetime(LocalDateTime.now());;
	        response.setUpdatedDatetime(LocalDateTime.now());
	        response.setSoftDeleted(0);
	        ArrayList<Customer> customerList = new ArrayList<>();
	        customerList.add(request);
	        
	        Mockito.lenient().when(customerRepo.findbyCountryIdAndMobile(request.getCountryId(),request.getMobile())).thenReturn(new ArrayList<>());
	        
	        // Mock repository to return the saved customer when save is called
	        Mockito.lenient().when(customerRepo.save(response)).thenReturn(response);
	        // Act
	        Customer result = customerService.create(request);
	        System.out.println("create fun ***result***getCountryId"+result.getCountryId());
	        System.out.println("create fun ***result***getFirstName=="+result.getFirstName());
	        System.out.println("create fun ***result***==getLastName"+result.getLastName());
	        System.out.println("create fun ***result***getMobile=="+result.getMobile());
	     
	        // Assert  
	        ArgumentCaptor<com.pcsinfotech.eodata.entities.Customer> customerCaptor = ArgumentCaptor.forClass(com.pcsinfotech.eodata.entities.Customer.class);
	        verify(customerRepo).save(customerCaptor.capture());
	        
	        assertEquals(1, result.getCountryId());
	        assertEquals("Vanitha", result.getFirstName());
	        assertEquals("Gururajan", result.getLastName());
	        assertEquals("9876543905", result.getMobile());
		 
	 }
	 
	 @Test
	 void testCreate_UpdateExistingCustomer() {
	       
		    Customer newCustomerRequest = new Customer();
	        newCustomerRequest.setCountryId(1L);
	        newCustomerRequest.setFirstName("Vanitha");
	        newCustomerRequest.setLastName("Gururajan");
	        newCustomerRequest.setMobile("9876543905");
	        newCustomerRequest.setIsoCode("+91");
	        newCustomerRequest.setTimeZone(11L);

	        com.pcsinfotech.eodata.entities.Customer  updatedCustomer = new  com.pcsinfotech.eodata.entities.Customer();
	      
	        updatedCustomer.setId(1L);
	        updatedCustomer.setCountryId(newCustomerRequest.getCountryId());
	        updatedCustomer.setFirstName(newCustomerRequest.getFirstName());
	        updatedCustomer.setLastName(newCustomerRequest.getLastName());
	        updatedCustomer.setMobile(newCustomerRequest.getMobile());
	        updatedCustomer.setSoftDeleted(0);
	        updatedCustomer.setTimeZoneId(newCustomerRequest.getTimeZone());
	        updatedCustomer.setVersionNumber(1);
	        updatedCustomer.setUpdatedDatetime(LocalDateTime.now());
	        
	        List<com.pcsinfotech.eodata.entities.Customer> customerList = new ArrayList<>();
	        customerList.add(updatedCustomer);

	        // Mock repository to return the existing customer for the given countryId and mobile
	        Mockito.lenient().when(customerRepo.findbyCountryIdAndMobile(newCustomerRequest.getCountryId(), newCustomerRequest.getMobile()))
	                .thenReturn((customerList));
	        // Mock repository to return the updated customer when save is called
	        Mockito.lenient().when(customerRepo.save(updatedCustomer)).thenReturn(updatedCustomer);

	        // Act
	        Customer result = customerService.create(newCustomerRequest);

	        // Capture the argument passed to the save method
	        ArgumentCaptor<com.pcsinfotech.eodata.entities.Customer> customerCaptor = ArgumentCaptor.forClass(com.pcsinfotech.eodata.entities.Customer.class);
	        verify(customerRepo).save(customerCaptor.capture());

	        // Assert
	        com.pcsinfotech.eodata.entities.Customer capturedCustomer = customerCaptor.getValue();
	        assertEquals(newCustomerRequest.getCountryId(), capturedCustomer.getCountryId());
	        assertEquals(newCustomerRequest.getFirstName(), capturedCustomer.getFirstName());
	        assertEquals(newCustomerRequest.getLastName(), capturedCustomer.getLastName());
	        assertEquals(newCustomerRequest.getMobile(), capturedCustomer.getMobile());
	      
	    }
	
}
	  
        
	
	

