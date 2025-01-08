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
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/calculatorEMI").hasAnyRole("EMPLOYEE").anyRequest()
				.authenticated()).formLogin(login -> login.loginPage("/login").permitAll())
				.exceptionHandling((exceptionHandling) ->exceptionHandling.accessDeniedPage("/accessDenied"))
				.logout(logout -> logout.permitAll());

		// http.csrf().disable();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication().withUser("akash").password("abcd").authorities("ROLE_EMPLOYEE")
						.and().withUser("neha").password("tech").authorities("ROLE_BOSS")
						.and().withUser("ayaan").password("tech").authorities("ROLE_EMPLOYEE");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}