package com.example.demo.customhealth;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class RemoteSvclHealthIndicator implements HealthIndicator {

	private static final String URL = "https://zwww.kjhsakfhwerweolldhfs.com/search?q=google";

	@Override
	public Health health() {
		// check if svc is reachable
		if (pingURL(URL, 5000)) {
			// all good
			return Health.up().withDetail("ok", "remote service is available").build();
		} else {
			// bad
			return Health.down().withDetail("nok", "remote service is NOT available").build();
		}

	}
	
	// Pings a HTTP URL.Sends a HEAD request and returns true if the response code is in the 200-399 range.
	// timeout in millis for both the connection timeout and the response read timeout. 		
	public static boolean pingURL(String url, int timeout) {
	    url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setConnectTimeout(timeout);
	        connection.setReadTimeout(timeout);
	        connection.setRequestMethod("HEAD");
	        int responseCode = connection.getResponseCode();
	        return (200 <= responseCode && responseCode <= 399);
	    } catch (IOException exception) {
	        return false;
	    }
	}

}
