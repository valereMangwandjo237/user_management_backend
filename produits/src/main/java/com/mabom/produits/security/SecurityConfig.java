package com.mabom.produits.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
		http.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf( csrf -> csrf.disable())
			//ce bloc permet à un site etranger (par exemple angular) d'acceder à ce microservice sans blocus
			.cors(cors -> cors.configurationSource(new CorsConfigurationSource(){
				
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					CorsConfiguration cors = new CorsConfiguration();
					cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
					cors.setAllowedMethods(Collections.singletonList("*"));
					cors.setAllowCredentials(true);
					cors.setAllowedHeaders(Collections.singletonList("*"));
					cors.setExposedHeaders(Collections.singletonList("Authorization"));
					cors.setMaxAge(3600L);
					
					return cors;
				}
				
			}))
			.authorizeHttpRequests( requests -> 
			requests.requestMatchers(HttpMethod.GET, "/api/all").hasAnyAuthority("ADMIN", "USER")
					.requestMatchers(HttpMethod.GET, "/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
					.requestMatchers(HttpMethod.POST, "/api/addprod").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/api/updateprod").hasAnyAuthority("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/deleteprod").hasAnyAuthority("ADMIN")
					.anyRequest().authenticated() )
					.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	
	}

}
