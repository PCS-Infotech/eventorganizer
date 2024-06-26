package com.pcsinfotech.eoapi.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-eoapi.xml")
public class EventOrganizer {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(EventOrganizer.class, args);
	}

}
