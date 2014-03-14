package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Note" database table.
 * 
 */
@Entity
@Table(name="\"Note\"")
@NamedQuery(name="Note.findAll", query="SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=500)
	private String note;

	@Column(name="written_user_id")
	private Integer writtenUserId;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="releated_patient_id")
	private Patient patient;

	public Note() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getWrittenUserId() {
		return this.writtenUserId;
	}

	public void setWrittenUserId(Integer writtenUserId) {
		this.writtenUserId = writtenUserId;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}