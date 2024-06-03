package com.pcsinfotech.eodata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcsinfotech.eodata.entities.OtpCode;

@Repository
public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {

}
