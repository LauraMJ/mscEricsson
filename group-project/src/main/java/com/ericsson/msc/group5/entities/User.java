package com.ericsson.msc.group5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * User JPA entity. Uses username as primary key.
 */
@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name = "findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username")})
public class User {

	@Id
	@Column(length = 32)
	private String username;
	@Column(length = 64)
	private String password;
	@Column(length = 32)
	private String role;

	/**
	 * No-args constructor used by the JPA.
	 */
	public User() {
	}

	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
