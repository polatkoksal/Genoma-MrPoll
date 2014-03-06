package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the "User" database table.
 * 
 */
public class UserUI implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String name;

	private String password;

	private String phone;

	private String surname;

	private String username;

	public UserUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}