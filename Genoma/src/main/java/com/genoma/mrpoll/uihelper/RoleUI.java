package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.genoma.mrpoll.domain.UserRole;

public class RoleUI implements Serializable{

	private static final long serialVersionUID = 1L;

	

	private Integer id;


	private String name;


	private ArrayList<UserRoleUI> userRoles;

	public RoleUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRoleUI> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(ArrayList<UserRoleUI> userRoles) {
		this.userRoles = userRoles;
	}

	
}
