package com.working.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.working.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/investor/**").hasRole("INVESTOR")
                .requestMatchers("/ia/**").hasRole("INVESTMENT_ADVISOR")
                .requestMatchers("/forgetPass/**").permitAll()
                .requestMatchers("/testForgetPass/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic();
        
        return http.build();
    }
    
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .requestMatchers("/forgetPass/investor/**")
            .requestMatchers("/forgetPass/ia/**");
    }
    
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }

    // Use AuthenticationManager to authenticate users with CustomUserDetailsService
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}