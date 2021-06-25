package com.cts.proj.app.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.proj.app.dao.AppUserDao;
import com.cts.proj.app.service.AppUserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AppUserService userRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	        FilterChain filterChain) throws ServletException, IOException {
		final String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("Request Header jwt Token: "+jwtToken);
		
		if (jwtToken==null || jwtToken.isEmpty()) {
			log.info("Invalid Credential");
			filterChain.doFilter(request, response);
			return;
		}
				
		UserDetails userDetails = userRepo
		        .loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));

		if (!jwtTokenUtil.validateToken(jwtToken, userDetails)) {
			filterChain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
		        userDetails, null, userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

}
