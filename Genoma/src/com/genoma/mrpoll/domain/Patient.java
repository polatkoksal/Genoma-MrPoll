package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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

	@Column(name="created_user_id")
	private Integer createdUserId;

	@Column(length=255)
	private String gender;

	@Column(length=255)
	private String namesurname;

	@Column(name="protocol_no")
	private Integer protocolNo;

	//bi-directional many-to-one association to Visit
	@OneToMany(mappedBy="patient")
	private Set<Visit> visits;

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