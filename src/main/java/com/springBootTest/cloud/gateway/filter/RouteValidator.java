package com.springBootTest.cloud.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.springBootTest.cloud.gateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    @Autowired
    private JwtUtil jwtUtil;

    public final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka");

    public final List<String> adminEndpoints = List.of(
            "/signup");

    public final List<String> providerEndpoints = List.of(
            "/providers");

    public final List<String> userEndpoints = List.of(
            "/events");

    public Predicate<ServerHttpRequest> isSecured = request -> {

        String requestedPath = request.getURI().getPath();

        // If requested path match any openApiEndpoints, return false
        if (openApiEndpoints.stream().anyMatch(uri -> requestedPath.contains(uri))) {
            return false;
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return true;
        }

        String token = authHeader.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(jwtUtil.getSignKey()).build().parseClaimsJws(token)
                .getBody();
        String role = claims.get("role", String.class);

        if (adminEndpoints.stream().anyMatch(uri -> requestedPath.contains(uri))) {
            return !"admin".equals(role);
        }

        return true;

    };
    /*
     * public Predicate<ServerHttpRequest> isSecured =
     * request -> {
     * String path = request.getURI().getPath();
     * if (openApiEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
     * return false;
     * }
     * String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
     * if (authHeader == null || !authHeader.startsWith("Bearer ")) {
     * return true;
     * }
     * String token = authHeader.substring(7);
     * Claims claims =
     * Jwts.parserBuilder().setSigningKey(jwtUtil.getSignKey()).build().
     * parseClaimsJws(token).getBody();
     * String role = claims.get("role", String.class);
     * if (adminEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
     * return "admin".equals(role);
     * }
     * if (userEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
     * return "user".equals(role);
     * }
     * if (providerEndpoints.stream().anyMatch(uri -> path.contains(uri))) {
     * return "provider".equals(role);
     * }
     * return true;
     * };
     */

    /*
     * public Predicate<ServerHttpRequest> isSecured =
     * request -> openApiEndpoints
     * .stream()
     * .noneMatch(uri -> request.getURI().getPath().contains(uri));
     */

}