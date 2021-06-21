package com.cts.proj.app;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableWebSecurity
@Slf4j
public class CustomSecurityApplication extends WebSecurityConfigurerAdapter{

	@Resource
	private UserDetailsService uds;
	
	/** Custom Web Security with User Details Service */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(uds);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
    }

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomSecurityApplication.class, args);
	}

}
