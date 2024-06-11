package com.pcsinfotech.eoservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pcsinfotech.eodata.entities.OtpCode;
import com.pcsinfotech.eodata.repositories.OtpCodeRepository;
import com.pcsinfotech.eoservices.model.ErrorCode;
import com.pcsinfotech.eoservices.model.IsoCodesList;
import com.pcsinfotech.eoservices.model.OTP;

@Service
public class OTPService {
	
	@Autowired
	private IsoCodesService isoCodesService;
	
	@Autowired
	private OtpCodeRepository otpCodeRepo;
	
	public OTP sendOTP(String country, String isoCode, String mobile) {
		final int defExpiration = 60;
		OTP returnValue = new OTP();
		returnValue.setOtpExpirationInSecs(0);
		returnValue.setError(ErrorCode.PCS_0);
		
		//Validate Mobile Number is Valid
		if ((!StringUtils.hasText(mobile)) || (mobile.length() != 10) || (!mobile.matches("\\d{10}"))) {
			returnValue.setError(ErrorCode.PCS_1);
			return returnValue;
		}
		
		//Validate Country and IsoCode Match and Exist
		IsoCodesList isoCodesList = this.isoCodesService.getIsoCodesByCountryAndIsoCode(country, isoCode);
		if (isoCodesList.getList().size() == 0) {
			returnValue.setError(isoCodesList.getError());
			return returnValue;
		}
		else {
			
			try {
				//Determine OTP
				String otp = mobile.substring(0, 5);
				//Insert OTP into the database
				OtpCode otpCodeDB = new OtpCode();
				otpCodeDB.setCountryId(isoCodesList.getList().get(0).getCountryDBId());
				otpCodeDB.setMobile(mobile);
				otpCodeDB.setOtpCode(otp);
				otpCodeDB.setExpiryTimeInSecs(defExpiration);
				otpCodeRepo.save(otpCodeDB);
				//return value
				returnValue.setOtp(otp);
				returnValue.setOtpExpirationInSecs(defExpiration);
				returnValue.setError(null);
				return returnValue;
			}
			catch (Exception e) {
				returnValue.setError(ErrorCode.PCS_3);
				return returnValue;
			}
		}
		
		
	}
}
