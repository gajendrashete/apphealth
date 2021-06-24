package com.example.demo.customhealth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

//@Configuration
public class DbPoolHealthIndicator extends AbstractHealthIndicator {

	@Autowired 
	HikariDataSource h2DataSource; 
	
	@Override
	protected void doHealthCheck(Builder builder) throws Exception {

		HikariPoolMXBean hikariPoolMXBean = h2DataSource.getHikariPoolMXBean(); 
		
		Map<String, String> cpDetails = new LinkedHashMap<String, String>(); 
		//
		cpDetails.put("PoolSize", "" + h2DataSource.getMaximumPoolSize()); 
		cpDetails.put("ActiveConnections", "" + hikariPoolMXBean.getActiveConnections()); 
		cpDetails.put("IdleConnections", "" + hikariPoolMXBean.getIdleConnections());
		cpDetails.put("Waiting Threads", "" + hikariPoolMXBean.getThreadsAwaitingConnection());
		cpDetails.put("AvilableConnections", "" + hikariPoolMXBean.getTotalConnections());
		
		if ( hikariPoolMXBean.getTotalConnections() == 0 ) { 
			builder.down().withDetail("Pool Exhausted", cpDetails); 
		} else { 
			builder.up().withDetail("Pool OK", cpDetails); 
		} 

	}

}
