package com.security.securityconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigApp {

	@Bean
	public SecurityFilterChain userDefiiinedFilter(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				request -> request
					.antMatchers("/","/about","/login")
					.permitAll()
					.anyRequest()
					.authenticated())
					.formLogin();
		return http.build();
	}
}
