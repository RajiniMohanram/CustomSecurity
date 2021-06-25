package com.cts.proj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.proj.app.service.AppUserService;
import com.cts.proj.app.util.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AppUserService userDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestParam("username") String userName, @RequestParam("password") String password) {
		log.info("Login with "+userName+" and "+password);
		defaultAuth(userName, password);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		log.info("User Details: "+userDetails);
		
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);
		log.info("JWT Token: "+jwtToken);
		
		return ResponseEntity.ok(jwtToken);
	}
	
	public void defaultAuth(String userName, String pass) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, pass);
			log.info("Authentication Token: "+token);
			authenticationManager.authenticate(token);
		}catch(DisabledException ex) {
			log.info("User disabled: "+ex.getMessage());
			throw ex;
		}catch(BadCredentialsException ex) {
			log.info("Invallid User credentials: "+ex.getMessage());
			throw ex;
		}
	}
}
