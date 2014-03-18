package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;



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

	@Column(name="belongs_question_id")
	private Integer belongsQuestionId;

	
	@ManyToOne
	@JoinColumn(name="answered_visit_id")
	private Visit visit;

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