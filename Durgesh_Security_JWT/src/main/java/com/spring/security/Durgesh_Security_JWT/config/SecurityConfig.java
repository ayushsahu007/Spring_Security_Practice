package com.spring.security.Durgesh_Security_JWT.config;

import com.spring.security.Durgesh_Security_JWT.security.JwtAuthenticationEntryPoint;
import com.spring.security.Durgesh_Security_JWT.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

        // configuration
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            // configuration
            http.csrf(csrf->csrf.disable())
                    .cors(cors->cors.disable())
                    .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated()
                            .requestMatchers("/auth/login").permitAll().anyRequest()
                            .authenticated())
                    .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }



}
