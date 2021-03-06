package com.genoma.mrpoll.uihelper;

import java.io.Serializable;


public class UserUI implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String email;

	private String hospital;

	private String name;

	private String password;

	private String phone;

	private String surname;

	private String username;

//	private ArrayList<DocumentUI> documents;
//
//	private ArrayList<PatientUI> patients;
//
//	private ArrayList<UserRoleUI> userRoles;

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

	public String getHospital() {
		return this.hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
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

	// public List<DocumentUI> getDocuments() {
	// return this.documents;
	// }
	//
	// public void setDocuments(ArrayList<DocumentUI> documents) {
	// this.documents = documents;
	// }
	//
	// public DocumentUI addDocument(DocumentUI document) {
	// getDocuments().add(document);
	// document.setUser(this);
	//
	// return document;
	// }
	//
	// public Document removeDocument(Document document) {
	// getDocuments().remove(document);
	// document.setUser(null);
	//
	// return document;
	// }
	//
	// public List<PatientUI> getPatients() {
	// return this.patients;
	// }
	//
	// public void setPatients(ArrayList<PatientUI> patients) {
	// this.patients = patients;
	// }
	//
	// public PatientUI addPatient(PatientUI patient) {
	// getPatients().add(patient);
	// patient.setUser(this);
	//
	// return patient;
	// }
	//
	// public PatientUI removePatient(PatientUI patient) {
	// getPatients().remove(patient);
	// patient.setUser(null);
	//
	// return patient;
	// }
	//
	// public List<UserRoleUI> getUserRoles() {
	// return this.userRoles;
	// }
	//
	// public void setUserRoles(ArrayList<UserRoleUI> userRoles) {
	// this.userRoles = userRoles;
	// }
	//
	// public UserRoleUI addUserRole(UserRoleUI userRole) {
	// getUserRoles().add(userRole);
	// userRole.setUser(this);
	//
	// return userRole;
	// }
	//
	// public UserRoleUI removeUserRole(UserRoleUI userRole) {
	// getUserRoles().remove(userRole);
	// userRole.setUser(null);
	//
	// return userRole;
	// }

}