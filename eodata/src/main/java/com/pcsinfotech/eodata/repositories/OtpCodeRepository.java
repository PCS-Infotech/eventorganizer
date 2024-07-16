package com.pcsinfotech.eodata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pcsinfotech.eodata.entities.OtpCode;

@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

	@Query("select u from OtpCode u where u.softDeleted = 0 and u.countryId = :id and u.mobile = :mobile and u.otpCode = :otpCode")
	List<OtpCode> findOtpbyCountryAndMobileAndOtp(@Param("id") Long id, @Param("mobile") String mobile,
			@Param("otpCode") String otpCode);

}
