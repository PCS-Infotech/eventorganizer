package com.pcsinfotech.eoapi.v1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsinfotech.eoapi.v1.model.*;
import com.pcsinfotech.eoservices.service.*;

@RestController
@RequestMapping(value="/v1")
public class IsoCodesController {
	
	@Autowired
	private IsoCodesService isoCodesService;
	
	/***
	 * GET: /v1/isoCodes end point to get the list of supported ISO Codes by Country. 
	 * @return
	 * 	IsoCodesResponse - List of ISO Codes
	 */
	@GetMapping(value = "/isoCodes", produces = "application/json")
	public IsoCodesResponse getIsoCodes() {
		
		IsoCodesResponse response = new IsoCodesResponse();
		response.setSuccess(true);
		response.setErrorCode(null);
		response.setErrorMessage(null);
		
		List<com.pcsinfotech.eoservices.model.IsoCode> isoCodesSM 
			= isoCodesService.getIsoCodes();
		
		// Build the IsoCode Collection for the response object 
		List<IsoCode> isoCodes = new ArrayList<IsoCode>();
		for (com.pcsinfotech.eoservices.model.IsoCode isoCode : isoCodesSM) {
			
			IsoCode item = new IsoCode();
			item.setCountry(isoCode.getCountry());
			item.setIsoCode(isoCode.getIsoCode());
			isoCodes.add(item);
		}
		
		response.setIsoCodes(isoCodes);
		return response;
	}

}
