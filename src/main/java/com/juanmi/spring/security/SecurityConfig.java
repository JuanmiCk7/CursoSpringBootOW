package com.juanmi.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.juanmi.spring.repositories.UserRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserRepository userRepository;
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 // Configure AuthenticationManagerBuilder
	    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    // Get AuthenticationManager
	    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		
        http
        .authorizeRequests()
        .antMatchers(filter()).permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .defaultSuccessUrl("/public/index", true)
        .loginProcessingUrl("/auth/login-post")
        .permitAll()
        .and()
        .authenticationManager(authenticationManager)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        .and()
        .logout()
        .logoutUrl("/auth/logout")
        .logoutSuccessUrl("/public/index");
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
        
        return http.build();
    }
	
	
	
	private String[] filter() {
		return new String[] {"/", "/webjars/**", "/h2-console/**","/css/**", "/public/**", "/js/**", "/img/**", "/lib/**", "/files/**", "/auth/**"};
	}
	
}
