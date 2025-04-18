package com.javaweb.demosb.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.demosb.dto.CustomUserDetails;
import com.javaweb.demosb.service.CustomUserDetailsService;
import com.javaweb.demosb.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userName;
            try{
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    jwt = authHeader.substring(7);
                    log.info("user jwt: {}", jwt);
                    userName = jwtService.extractUsername(jwt);
                    // get token info from jwt
                    CustomUserDetails customUserDetail = (CustomUserDetails) this.userDetailsService.loadUserByUsername(userName);
                    if (jwtService.isTokenValid(jwt, customUserDetail)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                customUserDetail,
                                null,
                                customUserDetail.getAuthorities()
                        );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);

            }
            catch (ExpiredJwtException ex) {
                logger.error("JWT Token has expired");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write(
                        buildJsonResponse("failed", null, "JWT Token has expired")
                );

            }
            catch (MalformedJwtException ex) {
                logger.error("Invalid JWT Token");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType("application/json");
                response.getWriter().write(
                        buildJsonResponse("failed", null, "Invalid JWT Token")
                );

            } catch (JwtException ex) {
                logger.error("JWT Token error: " + ex.getMessage());
                response.setStatus(HttpStatus.BAD_REQUEST.value());

                response.setContentType("application/json");
                response.getWriter().write(
                        buildJsonResponse("failed", null, ex.getMessage())
                );
            };
    }

    public String buildJsonResponse(String result, Object content, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("content", content);
        response.put("message", message);

        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to build JSON response", e);
        }
    }
}
