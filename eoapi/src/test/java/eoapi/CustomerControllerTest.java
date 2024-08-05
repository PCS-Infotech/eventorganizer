package eoapi;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.controller.CustomerController;
import com.pcsinfotech.eoapi.v1.model.CreateCustomerResponse;
import com.pcsinfotech.eoapi.v1.model.CustomerRequest;
import com.pcsinfotech.eoservices.model.Customer;
import com.pcsinfotech.eoservices.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;

	@Mock
	private ServiceToControllerMapper mapper;
	
	@Test	
	public void testcreateCustomer() {
		CustomerRequest coustreq= new CustomerRequest();
		
		coustreq.setFirstName("vanitha");
		coustreq.setLastName("Gururajan");
		coustreq.setEventType("Wedding");
		coustreq.setIsoCode("+91");
		coustreq.setMobile("9765432089");
				
		Customer mockcust = new Customer();
		
		mockcust.setCustomerId(1l);
		mockcust.setFirstName("vanitha");
		mockcust.setLastName("Gururajan");
		mockcust.setEventType("Wedding");
		mockcust.setIsoCode("+91");
		mockcust.setMobile("9765432089");
		
		CreateCustomerResponse response = new CreateCustomerResponse();
		response.setCustomerId(mockcust.getCustomerId());
	    
		Mockito.lenient().when(mapper.CustomerRequestToCustomer(coustreq)).thenReturn(mockcust);
		Mockito.lenient().when(customerService.createCountryisoCode(mockcust)).thenReturn(mockcust);
		mockcust.setCountryId(1l);
		Mockito.lenient().when(customerService.createtimeZone(mockcust)).thenReturn(mockcust);
		mockcust.setTimeZone(11l);
	    Mockito.lenient().when(customerService.create(mockcust)).thenReturn(mockcust);
	    Mockito.lenient().when(mapper.CustomerToCreateCustomerResponse(mockcust)).thenReturn(response);

	    CreateCustomerResponse result = customerController.createCustomer(coustreq);
	     
	    verify(mapper).CustomerRequestToCustomer(coustreq);
	    verify(customerService).createCountryisoCode(mockcust);
	    verify(customerService).createtimeZone(mockcust);
	    verify(customerService).create(mockcust);
		assertTrue(result.getSuccess());
	}		
}
