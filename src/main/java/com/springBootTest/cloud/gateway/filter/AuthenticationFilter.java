package com.springBootTest.cloud.gateway.filter;

import com.springBootTest.cloud.gateway.util.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    // @Autowired
    // private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {

                // if header does not contain token throw missing authorization exception
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                // if token exists in the header, validate it
                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new RuntimeException("invalid authorization header");
                }

                String token = authHeader.substring(7);

                try {
                    // validate the token
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("unauthorized access to application");
                }

                // check if user has required role for requested endpoint
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(jwtUtil.getSignKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                String role = claims.get("role", String.class);
                String path = exchange.getRequest().getPath().toString();

                if (validator.adminEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
                    if (!"admin".equals(role)) {
                        throw new RuntimeException("user is not authorized to access this resource");
                    }
                } else if (validator.providerEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
                    if (!"provider".equals(role)) {
                        throw new RuntimeException("user is not authorized to access this resource");
                    }
                } else if (validator.userEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
                    if (!"user".equals(role)) {
                        throw new RuntimeException("user is not authorized to access this resource");
                    }
                } else {
                    throw new RuntimeException("invalid or inaccessible resource path");
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}