package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//@RestController
//@RequestMapping("/api")
public class RestHealthController {

	
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
    
  
	@GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> appInfo() throws JsonProcessingException { 
		return ResponseEntity.ok().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoEndpoint.invoke()));
	}
	
	@GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> appHealth() throws JsonProcessingException { 
		return ResponseEntity.ok().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(healthEndpoint.invoke()));
	}
	
	@GetMapping(value = "/metric", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> appMetric() throws JsonProcessingException { 
		return ResponseEntity.ok().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metricsEndpoint.invoke()));
	}
	
	@GetMapping(value = "/beans", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> appBeans() throws JsonProcessingException { 
		return ResponseEntity.ok().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(beansEndpoint.invoke()));
	}
	@GetMapping(value = "/env", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> appEnv() throws JsonProcessingException { 
		return ResponseEntity.ok().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(environmentEndpoint.invoke()));
	}
	
	
	


	
}
