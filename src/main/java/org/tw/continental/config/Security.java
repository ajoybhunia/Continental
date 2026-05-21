package org.tw.continental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class Security {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
    
    httpSecurity
        .cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/users/*").permitAll()
            .requestMatchers("/api/search/hotels").permitAll()
            .requestMatchers("/api/bookings").permitAll()
            .anyRequest().authenticated()
        ).formLogin(AbstractHttpConfigurer::disable);

    return httpSecurity.build();
  }

}
