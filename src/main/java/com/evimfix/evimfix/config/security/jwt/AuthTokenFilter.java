package com.evimfix.evimfix.config.security.jwt;

import com.evimfix.evimfix.config.security.services.JwtUtils;
import com.evimfix.evimfix.core.utilities.results.ErrorResult;
import com.evimfix.evimfix.exception.model.codes.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Setter
@NoArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;


    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        try {
            // Clear SecurityContext before processing
            SecurityContextHolder.clearContext();

            // Parse JWT from request

            String authHeader = request.getHeader("Authorization");
            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);

                // If JWT is null or invalid, continue the filter chain
                if (!jwtUtils.validateJwtToken(jwt)) {
                    logger.warn("JWT is either null or invalid.");
                    filterChain.doFilter(request, response);
                    return;
                }


                // Extract JWT model and roles
                JwtModel jwtModel = this.jwtUtils.getJwtModelFromToken(jwt);

                if (jwtModel == null || jwtModel.getRoles() == null || jwtModel.getRoles().isEmpty()) {
                    logger.error("Invalid JWT model or missing roles: {}", jwt);
                    filterChain.doFilter(request, response);
                    return;
                }

                // Map roles to authorities
                Collection<? extends GrantedAuthority> authorities = jwtModel.getRoles()
                        .stream()
                        .map((eRole) -> new SimpleGrantedAuthority(eRole.name()))
                        .toList();

                // Set authentication in SecurityContextHolder
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(jwtModel, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }



        } catch (Exception e) {
            logger.error("Error processing filter: {}", e.getMessage());

            // Return a general error response
            ErrorResult errorResult = new ErrorResult(ErrorCode.UNEXPECTED_ERROR);

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(errorResult);

            response.getWriter().write(jsonResponse);
            return;
        }

        // Proceed with the filter chain if everything is fine
        filterChain.doFilter(request, response);
    }
}
