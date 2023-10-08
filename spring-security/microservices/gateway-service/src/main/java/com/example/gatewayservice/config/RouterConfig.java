package com.example.gatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Value("${routes.uri.product-service}")
    private String productService;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/productsvc/**")
                        .filters(f -> f.rewritePath("/productsvc/(?<path>.*)", "/$\\{path}"))
                        .uri(productService))
                .build();
    }
}
