package pl.kwi.springboot.commands;

import java.io.Serializable;

public class LoginCommand implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String username;
	private boolean rememberUsername;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isRememberUsername() {
		return rememberUsername;
	}
	public void setRememberUsername(boolean rememberUsername) {
		this.rememberUsername = rememberUsername;
	}	
		
	
}
