package com.genoma.mrpoll.uihelper;

import java.io.Serializable;

public class AnswerUI implements Serializable{

	
	private static final long serialVersionUID = 1L;


	
	private String answer;

	
	private String questionCode;


	public AnswerUI() {
	}

	
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionCode() {
		return this.questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

}
