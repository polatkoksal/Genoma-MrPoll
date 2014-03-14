package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.DATE)
	private Date date;

	private Integer gender;

	@Column(length=255)
	private String namesurname;

	@Column(name="protocol_no")
	private Integer protocolNo;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="patient")
	private List<Document> documents;

	//bi-directional many-to-one association to Note
	@OneToMany(mappedBy="patient")
	private List<Note> notes;

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

	public Integer getCreatedUserId() {
		return this.createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
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

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setPatient(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setPatient(null);

		return document;
	}

	public List<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Note addNote(Note note) {
		getNotes().add(note);
		note.setPatient(this);

		return note;
	}

	public Note removeNote(Note note) {
		getNotes().remove(note);
		note.setPatient(null);

		return note;
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