package com.cts.proj.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor
@ToString
public class AppUser {
	private String userId;
	private String password;
	private String userName;
	private String userMail;
	private String userCity; 
}
