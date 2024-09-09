package com.pcsinfotech.eoapi.controller;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ImportResource("classpath*:spring-eoapi.xml")
public class EventOrganizer {
	
	public static void main(String[] args) {
		/*
		 * TomcatURLStreamHandlerFactory.disable(); SpringApplication app = new
		 * SpringApplication(EventOrganizer.class);
		 * app.setWebApplicationType(WebApplicationType.SERVLET); app.run(args);
		 */
		ConfigurableApplicationContext run = SpringApplication.run(EventOrganizer.class, args);
	}
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
	    return factory -> factory.setContextPath("/eventorganizer");
	}

}
