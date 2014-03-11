package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Answer" database table.
 * 
 */
@Entity
@Table(name="\"Answer\"")
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=255)
	private String answer;

	@Column(name="answered_visit_id")
	private Integer answeredVisitId;

	@Column(name="belongs_question_id")
	private Integer belongsQuestionId;

	public Answer() {
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

	public Integer getAnsweredVisitId() {
		return this.answeredVisitId;
	}

	public void setAnsweredVisitId(Integer answeredVisitId) {
		this.answeredVisitId = answeredVisitId;
	}

	public Integer getBelongsQuestionId() {
		return this.belongsQuestionId;
	}

	public void setBelongsQuestionId(Integer belongsQuestionId) {
		this.belongsQuestionId = belongsQuestionId;
	}

}