package com.openclassrooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MediLaboGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediLaboGatewayApplication.class, args);
	}

	/*@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/patients/**").uri("http://localhost:9001"))
				.route(r -> r.path("/notes/**").uri("http://localhost:9002"))
				.route(r -> r.path("/assessments/**").uri("http://localhost:9003"))
				.build();
	}*/
}
