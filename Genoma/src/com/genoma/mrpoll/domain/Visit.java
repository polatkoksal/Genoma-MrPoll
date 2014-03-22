package com.genoma.mrpoll.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "Visit" database table.
 * 
 */
@Entity
@Table(name="\"Visit\"")
@NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Boolean ethic;

	@Column(length=255)
	private String hospital;

	@Column(length=500)
	private String note;

	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="done_patient_id")
	private Patient patient;

	public Visit() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getEthic() {
		return this.ethic;
	}

	public void setEthic(Boolean ethic) {
		this.ethic = ethic;
	}

	public String getHospital() {
		return this.hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}