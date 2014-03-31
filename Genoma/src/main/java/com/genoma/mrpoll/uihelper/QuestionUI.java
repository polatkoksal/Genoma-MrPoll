package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.genoma.mrpoll.domain.Answer;

public class QuestionUI implements Serializable{

	
	private static final long serialVersionUID = 1L;


	private Integer id;


	private String question;


	private String questionCode;


	private ArrayList<AnswerUI> answers;
	

	public QuestionUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionCode() {
		return this.questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public List<AnswerUI> getAnswers() {
		return this.answers;
	}

	public void setAnswers(ArrayList<AnswerUI> answers) {
		this.answers = answers;
	}

}
