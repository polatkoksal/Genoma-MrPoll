package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.Set;

import com.genoma.mrpoll.domain.Visit;

public class PatientUI implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer age;

	
	private Integer createdUserId;

	
	private String gender;

	
	private String namesurname;

	
	private Integer protocolNo;


	private Set<Visit> visits;

	public PatientUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNamesurname() {
		return this.namesurname;
	}

	public void setNamesurname(String namesurname) {
		this.namesurname = namesurname;
	}

	public Integer getProtocolNo() {
		return this.protocolNo;
	}

	public void setProtocolNo(Integer protocolNo) {
		this.protocolNo = protocolNo;
	}

	public Set<Visit> getVisits() {
		return this.visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}


}
