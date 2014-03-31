package com.genoma.mrpoll.uihelper;

import java.io.Serializable;

public class UserRoleUI implements Serializable{
	
	
	private static final long serialVersionUID = 1L;



	private Integer id;


	private RoleUI role;


	private UserUI user;

	public UserRoleUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleUI getRole() {
		return this.role;
	}

	public void setRole(RoleUI role) {
		this.role = role;
	}

	public UserUI getUser() {
		return this.user;
	}

	public void setUser(UserUI user) {
		this.user = user;
	}

}
