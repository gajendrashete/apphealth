package com.example.demo.customhealth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;

import com.zaxxer.hikari.HikariDataSource;

public class ApplicationInfoContributor implements org.springframework.boot.actuate.info.InfoContributor {

	@Override
	public void contribute(Builder builder) {
	
		Map<String, Object> details = new LinkedHashMap<String, Object>(); 
		//
		details.put("my", "great app");
		details.put("trivia", "fastest app in world");
		//
		builder.withDetails(details).build(); 
	}

}
