package com.yedy.gateway;

import com.yedy.gateway.interceptor.GatewayRequestFilter;
import com.yedy.gateway.interceptor.MyGatewayFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, MyGatewayFilter gatewayRequestFilter){
		return builder.routes()
				.route(p->p
						.path("/user/**")
						.filters(f->f.filter(gatewayRequestFilter))
						.uri("http://localhost:8081/"))
				.build();
	}
}
