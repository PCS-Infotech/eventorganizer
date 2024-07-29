package com.pcsinfotech.eoapi.v1.controller;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(IsoCodesController.class);

    @Autowired
    private IsoCodesService isoCodesService;
    
    @Autowired
    private ServiceToControllerMapper mapper;

    /**
     * GET: /v1/isoCodes endpoint to get the list of supported ISO Codes by Country. 
     * @return
     *     IsoCodesResponse - List of ISO Codes
     */
    @GetMapping(value = "/isoCodes", produces = "application/json")
    public IsoCodesResponse getIsoCodes() {
        logger.info("Received request to get ISO codes");
        
        IsoCodesResponse response = new IsoCodesResponse();
        try {
            List<com.pcsinfotech.eoservices.model.IsoCode> isoCodesSM = isoCodesService.getIsoCodes();
            logger.debug("Retrieved {} ISO codes from service", isoCodesSM.size());
            
            List<com.pcsinfotech.eoapi.v1.model.IsoCode> isoCodes = mapper.listofIsoCodeToListOfIsoCode(isoCodesSM);
            response.setIsoCodes(isoCodes);
            
            logger.info("Successfully mapped ISO codes and returning response");
        } catch (Exception e) {
            logger.error("Error occurred while processing ISO codes", e);
        }
        
        return response;
    }
}
