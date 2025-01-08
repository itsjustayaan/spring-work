package com.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/", "/customer").permitAll()
				.requestMatchers("/hello").hasAnyRole("TRAINEE").requestMatchers("/index").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/viewAllProducts").hasAnyRole("ADMIN")
				.requestMatchers("/addProduct")
				.hasAnyRole("ADMIN").requestMatchers("/updateProduct").hasAnyRole("CONSULTANT").anyRequest()
				.authenticated()).formLogin(login -> login.loginPage("/login").permitAll())
				//.authenticated()).formLogin()
				.logout(logout -> logout.permitAll());

		// http.csrf().disable();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication().withUser("akash").password("abcd").authorities("ROLE_TRAINEE")
						.and().withUser("neha").password("tech").authorities("ROLE_USER")
						.and().withUser("tufail").password("ahmed").authorities("ROLE_USER", "ROLE_ADMIN")
						.and().withUser("admin").password("admin").authorities("ROLE_ADMIN")
						.and().withUser("harsh").password("bankpass").authorities("ROLE_CONSULTANT");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}