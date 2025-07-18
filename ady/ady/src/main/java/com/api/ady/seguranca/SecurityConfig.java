package com.api.ady.seguranca;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth").permitAll()
				.anyRequest().authenticated()
				)
		.sessionManagement(session -> session
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.addFilterBefore(autenticacaoViaTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	/*
	 
	 */
@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
	return authenticationConfiguration.getAuthenticationManager();
}

@Bean 
PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
@Bean
AutenticacaoViaTokenFilter autenticacaoViaTokenFilter() {
	return new AutenticacaoViaTokenFilter();
	
}
@Bean 
UrlBasedCorsConfigurationSource configurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("http://localhost:5500", "http://127.0.0.1:5500"));
	configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
	configuration.setAllowedHeaders(Arrays.asList("*"));
	configuration.setAllowCredentials(true);
	
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
}
}
