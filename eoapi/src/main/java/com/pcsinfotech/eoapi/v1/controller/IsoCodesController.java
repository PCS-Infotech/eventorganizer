package com.pcsinfotech.eoapi.v1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsinfotech.eoapi.v1.Mapping.ServiceToControllerMapper;
import com.pcsinfotech.eoapi.v1.model.*;
import com.pcsinfotech.eoservices.service.*;

@RestController
@RequestMapping(value="/v1")
public class IsoCodesController {
	
	@Autowired
	private IsoCodesService isoCodesService;
	
	@Autowired
	private ServiceToControllerMapper mapper; 
	
	/***
	 * GET: /v1/isoCodes end point to get the list of supported ISO Codes by Country. 
	 * @return
	 * 	IsoCodesResponse - List of ISO Codes
	 */
	@GetMapping(value = "/isoCodes", produces = "application/json")
	public IsoCodesResponse getIsoCodes() {
		IsoCodesResponse response = new IsoCodesResponse();
		List<com.pcsinfotech.eoservices.model.IsoCode> isoCodesSM = isoCodesService.getIsoCodes();
		List<com.pcsinfotech.eoapi.v1.model.IsoCode> isoCodes = mapper.listofIsoCodeToListOfIsoCode(isoCodesSM);
		response.setIsoCodes(isoCodes);
		return response;
	}

}
