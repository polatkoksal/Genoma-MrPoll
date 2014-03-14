package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Document" database table.
 * 
 */
@Entity
@Table(name="\"Document\"")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="creates_user_id")
	private Integer createsUserId;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(length=255)
	private String link;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="attached_patient_id")
	private Patient patient;

	public Document() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatesUserId() {
		return this.createsUserId;
	}

	public void setCreatesUserId(Integer createsUserId) {
		this.createsUserId = createsUserId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}