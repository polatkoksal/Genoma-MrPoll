package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.List;

public class Container implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private PatientUI patient;
	private VisitUI visit;
	private List<AnswerUI> answers;
	
	
	public Container(){
		
	}


	public PatientUI getPatient() {
		return patient;
	}


	public void setPatient(PatientUI patient) {
		this.patient = patient;
	}


	public VisitUI getVisit() {
		return visit;
	}


	public void setVisit(VisitUI visit) {
		this.visit = visit;
	}


	public List<AnswerUI> getAnswers() {
		return answers;
	}


	public void setAnswers(List<AnswerUI> answers) {
		this.answers = answers;
	}
	
	

	
}
