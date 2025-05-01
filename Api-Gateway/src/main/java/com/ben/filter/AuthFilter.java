package com.ben.filter;

import com.ben.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final RouteValidator routeValidator;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            if(routeValidator.isSecured.test(exchange.getRequest())) {

                //header contains token
                if(!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                    throw new RuntimeException("Unauthorized");
                }

                String token = exchange.getRequest().getHeaders().get("Authorization").get(0);

                if(token != null && token.startsWith("Bearer ")) {
                    token = token.substring(7);
                }

                try {
//                    // Rest call to Auth Service
//                    restTemplate.getForObject("http://localhost:8080/api/auth/validate?token=" + token, String.class);
                    jwtUtil.validateToken(token);

                }catch (Exception e) {
                    throw new RuntimeException("Unauthorized");
                }

            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }

    public AuthFilter(RouteValidator routeValidator, RestTemplate restTemplate, JwtUtil jwtUtil) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }
}
