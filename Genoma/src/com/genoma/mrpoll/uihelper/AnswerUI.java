package com.genoma.mrpoll.uihelper;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.genoma.mrpoll.domain.Visit;

public class AnswerUI implements Serializable{

	
	private static final long serialVersionUID = 1L;


	private Integer id;

	
	private String answer;


	private Integer belongsQuestionId;

	
	private Visit visit;

	public AnswerUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getBelongsQuestionId() {
		return this.belongsQuestionId;
	}

	public void setBelongsQuestionId(Integer belongsQuestionId) {
		this.belongsQuestionId = belongsQuestionId;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
}
