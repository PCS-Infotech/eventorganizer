package com.pcsinfotech.eoservices.service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.pcsinfotech.eodata.entities.Country;
import com.pcsinfotech.eodata.entities.Customer;
import com.pcsinfotech.eodata.entities.Invitee;
import com.pcsinfotech.eodata.entities.Organizer;
import com.pcsinfotech.eodata.entities.OtpCode;
import com.pcsinfotech.eodata.repositories.CountryRepository;
import com.pcsinfotech.eodata.repositories.CustomerRepository;
import com.pcsinfotech.eodata.repositories.InviteeRepository;
import com.pcsinfotech.eodata.repositories.OrganizerRepository;
import com.pcsinfotech.eodata.repositories.OtpCodeRepository;
import com.pcsinfotech.eoservices.model.AuthToken;
import com.pcsinfotech.eoservices.model.CustomerType;
import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
import com.pcsinfotech.eoservices.model.OTP;

@Service
public class OTPService {

	@Autowired
	private IsoCodesService isoCodesService;

	@Autowired
	private OtpCodeRepository otpCodeRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private InviteeRepository inviteeRepo;

	@Autowired
	private OrganizerRepository organizerRepo;

	private boolean validateMobile(String mobile) {
		if ((!StringUtils.hasText(mobile)) || (mobile.length() != 10) || (!mobile.matches("\\d{10}"))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean validateOtpCode(String otpCode) {
		if ((!StringUtils.hasText(otpCode)) || (otpCode.length() != 6) || (!otpCode.matches("\\d{6}"))) {
			return false;
		} else {
			return true;
		}
	}

	private boolean validateIsoCode(String isoCode) {
		if (!StringUtils.hasText(isoCode)) {
			return false;
		} else {
			return true;
		}
	}

	private String getNextAuthToken() {
		// Then generate new AuthToken - For this we need to discuss with Yuvaraj.
		return "XYZKSHGCKSDBHCK";
	}

	public OTP sendOTP(String country, String isoCode, String mobile) {
		final int defExpiration = 60;
		OTP returnValue = new OTP();
		returnValue.setOtpExpirationInSecs(0);
		returnValue.setError(ErrorCode.PCS_0);

		// Validate Mobile Number is Valid
		if (!this.validateMobile(mobile)) {
			returnValue.setError(ErrorCode.PCS_1);
			return returnValue;
		}

		// Validate Country and IsoCode Match and Exist
		IsoCodesList isoCodesList = this.isoCodesService.getIsoCodesByCountryAndIsoCode(country, isoCode);
		if (isoCodesList.getList().size() == 0) {
			returnValue.setError(isoCodesList.getError());
			return returnValue;
		} else {
			try {
				// Determine OTP
				String otp = mobile.substring(0, 5);
				// Insert OTP into the database
				OtpCode otpCodeDB = new OtpCode();
				otpCodeDB.setCountryId(isoCodesList.getList().get(0).getCountryDBId());
				otpCodeDB.setMobile(mobile);
				otpCodeDB.setOtpCode(otp);
				otpCodeDB.setExpiryTimeInSecs(defExpiration);
				otpCodeRepo.save(otpCodeDB);
				// return value
				returnValue.setOtp(otp);
				returnValue.setOtpExpirationInSecs(defExpiration);
				returnValue.setError(null);
				return returnValue;
			} catch (Exception e) {
				returnValue.setError(ErrorCode.PCS_3);
				return returnValue;
			}
		}
	}

	public AuthToken verifyOtp(String otpCode, String isoCode, String mobile) {

		// Verify optCode, isoCode and mobile are not null. If null return response with
		// error code and error message

		AuthToken authToken = new AuthToken();

		// Validate Mobile Number is Valid
		if (!this.validateMobile(mobile)) {
			authToken.setAuthToken(null);
			authToken.setCustomerType(null);
			authToken.setError(ErrorCode.PCS_1);
			return authToken;
		}

		// Validate OTP code is Valid
		if (!this.validateOtpCode(otpCode)) {
			authToken.setAuthToken(null);
			authToken.setCustomerType(null);
			authToken.setError(ErrorCode.PCS_16);
			return authToken;
		}

		// Validate IsoCode is Valid
		if (!this.validateIsoCode(isoCode)) {
			authToken.setAuthToken(null);
			authToken.setCustomerType(null);
			authToken.setError(ErrorCode.PCS_14);
			return authToken;

		}

		// Verify if otpCode was previously created for passed in isoCode and mobile
		// number

		// Finding country record
		Country country = new Country();
		List<Country> dbCountries = countryRepo.findCountriesByIsoCode(isoCode);
		if (!CollectionUtils.isEmpty(dbCountries)) {
			dbCountries.stream().forEach(t -> {
				country.setId(t.getId());
				country.setCountry(t.getCountry());
				country.setIsoCode(t.getIsoCode());

			});
		} else {
			authToken.setError(ErrorCode.PCS_15);
			return authToken;
		}

		// Finding OtpCodes
		OtpCode code = new OtpCode();
		List<OtpCode> dbOtpCodes = otpCodeRepo.findOtpbyCountryAndMobileAndOtp(country.getId(), mobile, otpCode);
		if (!CollectionUtils.isEmpty(dbOtpCodes)) {
			dbOtpCodes.stream().forEach(t -> {
				code.setMobile(t.getMobile());
				code.setOtpCode(t.getOtpCode());
				code.setExpiryTimeInSecs(t.getExpiryTimeInSecs());
				code.setCreatedDateTime(t.getCreatedDateTime());
			});
		} else {
			authToken.setError(ErrorCode.PCS_4);
			return authToken;
		}

		// If otpCode is for the passed in isocode and mobile ensure it is not expired.
		ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("UTC"));

		// Difference is between CurrentTime and CreatedTime(CurrentTime - CreatedTime)
		Duration duration = Duration.between(code.getCreatedDateTime().toLocalDateTime(),
				currentTime.toLocalDateTime());

		if (duration.getSeconds() > code.getExpiryTimeInSecs()) {
			authToken.setError(ErrorCode.PCS_5);
			return authToken;
		}

		// Check customer status and return according to that customer.
		Customer customer = new Customer();
		List<Customer> dbCustomers = customerRepo.findCustomerByCountryIdAndMobile(country.getId(), mobile);
		if (!CollectionUtils.isEmpty(dbCustomers)) {
			dbCustomers.stream().forEach(t -> {
				customer.setCountryId(t.getCountryId());
				customer.setFirstName(t.getFirstName());
				customer.setLastName(t.getLastName());
				customer.setMobile(t.getMobile());
				customer.setId(t.getId());
			});
		} else {
			authToken.setAuthToken(this.getNextAuthToken());
			authToken.setCustomerType(CustomerType.NEW);
			authToken.setError(null);
			return authToken;
		}

		boolean isInvitee = false;
		List<Invitee> dbInvitees = inviteeRepo.findInviteeByCustomerId(customer.getId());
		if (!CollectionUtils.isEmpty(dbInvitees)) {
			isInvitee = true;
		}

		boolean isOrganizer = false;
		List<Organizer> dbOrganizers = organizerRepo.findOrganizerByCustomerId(customer.getId());
		if (!CollectionUtils.isEmpty(dbOrganizers)) {
			isOrganizer = true;
		}

		if (isInvitee && isOrganizer) {
			authToken.setAuthToken(this.getNextAuthToken());
			authToken.setCustomerType(CustomerType.BOTH);
			authToken.setError(null);
			return authToken;
		} else if (isInvitee) {
			authToken.setAuthToken(this.getNextAuthToken());
			authToken.setCustomerType(CustomerType.INVITEE);
			authToken.setError(null);
			return authToken;
		} else if (isOrganizer) {
			authToken.setAuthToken(this.getNextAuthToken());
			authToken.setCustomerType(CustomerType.ORGANIZER);
			authToken.setError(null);
			return authToken;
		} else {
			authToken.setAuthToken(this.getNextAuthToken());
			authToken.setCustomerType(CustomerType.NEW);
			authToken.setError(null);
			return authToken;
		}

	}
}
