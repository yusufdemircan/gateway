package com.yedy.gateway.interceptor;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class MyGatewayFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("İstek URL: " + exchange.getRequest().getURI());
        String token = "";
        if(!Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).isEmpty()){
            token = Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).get(0);
        }
        if(token.contains("yedy")){
            return chain.filter(exchange);
        }
        else return null;
    }

}