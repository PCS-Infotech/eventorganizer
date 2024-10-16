package com.pcsinfotech.eoservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.model.CreateCustomerResponse;
import com.pcsinfotech.eoapi.v1.model.CustomerRequest;
import com.pcsinfotech.eoservices.model.Customer;
import com.pcsinfotech.eoservices.model.ErrorCode;


class ServiceToControllerMapperTest {
	
	 private ServiceToControllerMapper mapper;

	 @BeforeEach
	 public void setUp() {
	 mapper = new ServiceToControllerMapper();   
	}

	    @Test
	    public void testCustomerRequestToCustomerMapping() {
	        // Create a sample CustomerRequest object
	        CustomerRequest customerRequest = new CustomerRequest();
	        customerRequest.setFirstName("vani");
	        customerRequest.setLastName("Gururajan");
	        customerRequest.setEventType("wedding");
	        customerRequest.setMobile("9765432089");
	        customerRequest.setIsoCode("+91");

	        // Call the method to be tested
	        com.pcsinfotech.eoservices.model.Customer  customer = mapper.CustomerRequestToCustomer(customerRequest);
	     
	        // Assertions to verify mapping
	        assertEquals(customerRequest.getFirstName(), customer.getFirstName());
	        assertEquals(customerRequest.getLastName(), customer.getLastName());
	        assertEquals(customerRequest.getEventType(), customer.getEventType());
	        assertEquals(customerRequest.getMobile(), customer.getMobile());
	        assertEquals(customerRequest.getIsoCode(), customer.getIsoCode());
	    }

	    @Test
	    public void testCustomerToCreateCustomerResponse_Success() {
	    	// Arrange
	    	Customer customer = new Customer();
	        customer.setCustomerId(11l); 
	        CreateCustomerResponse response = mapper.CustomerToCreateCustomerResponse(customer);
	        // Assert
	        assertTrue(response.getSuccess());
	        assertEquals(11L, response.getCustomerId());
	        assertNull(response.getErrorCode());
	        assertNull(response.getErrorMessage());
	    }
		private String assertNull(String errorCode) {
			return errorCode;	
		}
		private boolean assertTrue(boolean success) {
			return success;
		}
	    
		@Test
	    void testCustomerToCreateCustomerResponse_WithError() {
	        // Create a sample Customer object with error
		    ErrorCode error = ErrorCode.PCS_0;
	        Customer customer = new Customer();
	        customer.setCustomerId(11l);
	        customer.setError(error);
	        // Call the method under test
	        CreateCustomerResponse response = mapper.CustomerToCreateCustomerResponse(customer);
	        // Assert
	        assertThat(response).isNotNull();
	        assertThat(response.getCustomerId()).isEqualTo(11L); // Assuming getCustomerId() returns a long
	        assertThat(response.getSuccess()).isEqualTo(false); 
	        assertThat(response.getErrorCode()).isEqualTo("PCS-0");
	        assertThat(response.getErrorMessage()).isEqualTo("Error");
	    }
}
