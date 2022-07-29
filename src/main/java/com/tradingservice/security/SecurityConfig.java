package com.tradingservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true, proxyTargetClass = true)
public class SecurityConfig {

	  @Autowired
	    private UserDetailsService userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .userDetailsService(userDetailsService)
	            .passwordEncoder(passwordEncoder());
	    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                .mvcMatchers("/css/**", "/js/**");
        }
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .antMatcher("/ingestion/**").authorizeRequests()
				// .anyRequest().permitAll()
				.anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint());
            http
            .headers()            
            .cacheControl(); 
        }
    }

	
}
