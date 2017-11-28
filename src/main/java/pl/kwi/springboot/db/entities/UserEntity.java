package pl.kwi.springboot.db.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntity {

	
	@JsonProperty(required = false)
	private String name;
	
	
	public UserEntity() {}
	
	public UserEntity(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
