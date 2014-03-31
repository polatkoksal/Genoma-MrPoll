package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatientUI implements Serializable {
	

	private static final long serialVersionUID = 1L;


	private Integer id;

	private Integer age;

	
	private String gender;

	
	private String nameSurname;

	
	private String protocolNo;




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

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNameSurname() {
		return this.nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String getProtocolNo() {
		return this.protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

}
