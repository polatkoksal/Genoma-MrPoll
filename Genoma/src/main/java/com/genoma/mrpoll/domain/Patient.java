package com.genoma.mrpoll.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the "Patient" database table.
 * 
 */
@Entity
@Table(name="\"Patient\"")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	private Integer age;

	@Column(length=255)
	private String gender;

	@Column(name="name_surname", length=255)
	private String nameSurname;

	@Column(name="protocol_no", length=255)
	private String protocolNo;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_user_id")
	private User user;

	//bi-directional many-to-one association to Visit
	@OneToMany(mappedBy="patient")
	private List<Visit> visits;

	public Patient() {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Visit> getVisits() {
		return this.visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Visit addVisit(Visit visit) {
		getVisits().add(visit);
		visit.setPatient(this);

		return visit;
	}

	public Visit removeVisit(Visit visit) {
		getVisits().remove(visit);
		visit.setPatient(null);

		return visit;
	}

}