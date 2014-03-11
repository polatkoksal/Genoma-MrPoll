package com.genoma.mrpoll.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Question" database table.
 * 
 */
@Entity
@Table(name="\"Question\"")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="contain_answer_id")
	private Integer containAnswerId;

	@Column(length=255)
	private String question;

	public Question() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContainAnswerId() {
		return this.containAnswerId;
	}

	public void setContainAnswerId(Integer containAnswerId) {
		this.containAnswerId = containAnswerId;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}