package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Document;
import com.genoma.mrpoll.domain.Patient;

public class VisitUI implements Serializable{

	private static final long serialVersionUID = 1L;



	private Integer id;

	private Date date;

	private Boolean ethic;

	private String hospital;

	private String note;

	private ArrayList<AnswerUI> answers;

	private ArrayList<DocumentUI> documents;

	private PatientUI patient;

	public VisitUI() {
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

	public List<AnswerUI> getAnswers() {
		return this.answers;
	}

	public void setAnswers(ArrayList<AnswerUI> answers) {
		this.answers = answers;
	}

	public List<DocumentUI> getDocuments() {
		return this.documents;
	}

	public void setDocuments(ArrayList<DocumentUI> documents) {
		this.documents = documents;
	}


	public PatientUI getPatient() {
		return this.patient;
	}

	public void setPatient(PatientUI patient) {
		this.patient = patient;
	}

	
}
