package com.cts.proj.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cts.proj.app.model.AppUser;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AppUserDao {
	private Map<String,AppUser> users;
	
	public AppUserDao() {
		AppUser user1 = new AppUser("erik", "magneto", "Erik", "erik@xmen.com", "Soviet");
		AppUser user2 = new AppUser("charles","professor","Charles Xavier","charles@xmen.com","British");
		AppUser user3 = new AppUser("logan", "wolverine", "Logan", "logan@xmen.com", "Canada");
		
		users = new HashMap<String, AppUser>();
		users.put(user1.getUserId(), user1);
		users.put(user2.getUserId(), user2);
		users.put(user3.getUserId(), user3);
		
		log.info("Initialized users map");
		
	}
	
	public AppUser findUser(String userId) {
		return users.get(userId);
	}
}
