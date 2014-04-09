package com.genoma.mrpoll.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "Answer" database table.
 * 
 */
@Entity
@Table(name = "\"Answer\"")
@NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(name = "answer_value", length = 255)
	private String answerValue;

	// bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "belongs_question_id")
	private Question question;

	// bi-directional many-to-one association to Visit
	@ManyToOne
	@JoinColumn(name = "answered_visit_id")
	private Visit visit;

	public Answer() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerValue() {
		return this.answerValue;
	}

	public void setAnswerValue(String answer) {
		this.answerValue = answer;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}