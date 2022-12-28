//package com.example.bookingsystem.securityconfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import lombok.RequiredArgsConstructor;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//	
////	private final UserDetailsService userDetailsService;
////	private final PasswordEncoder passwordEncoder;
////	private final SecurityFilterAutoConfiguration autoConfiguration;
//	@Autowired
//	private final UserDetailsService userDetailsService;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private InvalidUserAuthEntryPoint authEntryPoint;
//	
//	private final String ADMIN = "ADMIN";
//	private final String User = "USER";
//	
//	/* for authentication */
//	
//	@Override
//	@Bean
//		protected AuthenticationManager authenticationManager() throws Exception {
//			// TODO Auto-generated method stub
//			return super.authenticationManager();
//		}
//	
//	  @Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth
//			.userDetailsService(userDetailsService)
//			.passwordEncoder(passwordEncoder); 
//		}
//	
////	  /*for Authorization */	  
////	  @Override
////		protected void configure(HttpSecurity http) throws Exception {
////			http
////				.authorizeRequests()
////				.antMatcher("//booking/user/**")
////		}
////	
//	 }
