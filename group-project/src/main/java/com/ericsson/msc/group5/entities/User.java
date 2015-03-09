package com.ericsson.msc.group5.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="user")
@NamedQueries({@NamedQuery(name = "findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String username;

	private String password;

	private int userType;
	
	public User(){
		
	}
	
	public User(String username, String password, int userType){
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
