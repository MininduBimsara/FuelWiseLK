package com.example.Back_End.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF if required
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/VehicleOwner/save").permitAll()
                        .requestMatchers("/api/v1/VehicleOwner/login").permitAll()// Allow public access to this endpoint
                        .requestMatchers("/api/v1/FuelStation/save").permitAll()
                        .requestMatchers("/api/v1/FuelStation/getStations").permitAll()
                        .requestMatchers("/api/v1/FuelStation/get").permitAll()// Allow public access to this endpoint
                        .anyRequest().authenticated()  // All other endpoints require authentication
                );

        return http.build();  // Return the SecurityFilterChain
    }
}
