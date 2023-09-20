package com.security.securityconfiguration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigApp {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enadled from users where username = ?")
		.authoritiesByUsernameQuery("select username,authority from authorites where username =?");
	}
	
	@Bean
	public SecurityFilterChain customizeFilterSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.antMatchers("/app/").permitAll()
				.antMatchers("/app/admin/").hasRole("ADMIN")
				.antMatchers("/app/user/").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated())
				.formLogin();
		return http.build();
	}
}
