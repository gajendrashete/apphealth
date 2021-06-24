package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.customhealth.GreeterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/api")
public class AppHealthController {

	
	@Autowired 
	GreeterService greeterService; 
	
	@Autowired
	InfoEndpoint infoEndpoint;
	@Autowired
	HealthEndpoint healthEndpoint;
	@Autowired
	MetricsEndpoint metricsEndpoint;
	@Autowired
	BeansEndpoint beansEndpoint;
	@Autowired
	EnvironmentEndpoint environmentEndpoint;
	
	ObjectMapper mapper = new ObjectMapper();
    
   
	@RequestMapping("/info")
	public ModelAndView appInfo() throws JsonProcessingException {
		return new ModelAndView("health", "response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoEndpoint.invoke()));
	}
	
	@RequestMapping("/health")
	public ModelAndView appHealth() throws JsonProcessingException {
		return new ModelAndView("health", "response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(healthEndpoint.invoke()));
	}
	
	@RequestMapping("/metric")
	public ModelAndView appMetric() throws JsonProcessingException {
		return new ModelAndView("health", "response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metricsEndpoint.invoke()));
	}
	
	
	@RequestMapping("/beans")
	public ModelAndView appBeans() throws JsonProcessingException {
		return new ModelAndView("health", "response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(beansEndpoint.invoke()));
	}
	
	@RequestMapping("/env")
	public ModelAndView appEnv() throws JsonProcessingException {
		return new ModelAndView("health", "response", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(environmentEndpoint.invoke()));
	}


	
}
