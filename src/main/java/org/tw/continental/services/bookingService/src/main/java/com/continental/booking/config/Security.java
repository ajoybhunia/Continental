package com.continental.booking.config;

import com.continental.booking.filters.JWTFilter;
import com.continental.booking.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {

	private final JWTFilter jwtFilter;
	private final UserService userService;

	public Security(JWTFilter jwtFilter,  UserService userService) {
		this.jwtFilter = jwtFilter;
		this.userService = userService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity
						.cors(Customizer.withDefaults())
						.csrf(AbstractHttpConfigurer::disable)
						.authorizeHttpRequests(auth -> auth
														.requestMatchers("/api/users/*").permitAll()
														.requestMatchers("/api/search/hotels").permitAll()
//            .requestMatchers("/api/bookings").permitAll()
														.anyRequest().authenticated()
						)
						.sessionManagement(session -> session
										.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
						.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
						.authenticationProvider(authenticationProvider())
						.formLogin(AbstractHttpConfigurer::disable);

		return httpSecurity.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService);

		return authProvider;
	}

}
