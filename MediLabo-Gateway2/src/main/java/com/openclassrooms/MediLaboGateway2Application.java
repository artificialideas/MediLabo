package com.openclassrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediLaboGateway2Application {

	public static void main(String[] args) {
		SpringApplication.run(MediLaboGateway2Application.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/patients/**").uri("http://localhost:9001"))
				.route(r -> r.path("/notes/**").uri("http://localhost:9002"))
				.route(r -> r.path("/assessments/**").uri("http://localhost:9003"))
				.build();
	}
}
