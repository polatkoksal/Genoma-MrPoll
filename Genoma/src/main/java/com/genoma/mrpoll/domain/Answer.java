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

	//bi-directional many-to-one association to Question
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="belongs_question_id")
	private Question question;

	//bi-directional many-to-one association to Visit
	@ManyToOne(cascade = CascadeType.PERSIST)
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