package com.example.boot10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {
		Info info = new Info()
				.title("API 문서 입니다")
				.version("1.0")
				.description("api with swagger");
		OpenAPI api = new OpenAPI().info(info);
		
		return api;
	}
}
