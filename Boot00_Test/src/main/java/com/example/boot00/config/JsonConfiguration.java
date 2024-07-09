package com.example.boot00.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JsonConfiguration {

	@Value("${json.server.url}")
	private String jsonServerUrl;
	
	@Bean
	public String jsonServerUrl() {
		return jsonServerUrl;
	}
}



