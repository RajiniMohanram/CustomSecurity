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

import com.cts.proj.app.util.JwtTokenFilter;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CustomSecurityApplication{
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomSecurityApplication.class, args);
	}

}
