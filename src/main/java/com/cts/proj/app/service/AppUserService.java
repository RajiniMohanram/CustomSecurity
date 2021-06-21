package com.cts.proj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.proj.app.dao.AppUserDao;
import com.cts.proj.app.model.AppUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserService implements UserDetailsService{
	@Autowired
	private AppUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AppUser appUser = userDao.findUser(username);
		log.info("AppUser info in UserDetailService: "+appUser);
		if(appUser == null) {
			throw new UsernameNotFoundException("User with "+username+" id not found");
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserDetails user = User.withUsername(appUser.getUserId()).password(encoder.encode(appUser.getPassword())).authorities("USER").build();
		
		log.info("User build done with password in UserDetailsService "+user.getPassword());
		return user;
	}
}
