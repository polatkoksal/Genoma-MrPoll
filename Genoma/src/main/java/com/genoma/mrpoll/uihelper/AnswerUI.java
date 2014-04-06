package com.genoma.mrpoll.uihelper;

import java.io.Serializable;

public class AnswerUI implements Serializable{

	
	private static final long serialVersionUID = 1L;


	
	private String answerValue;

	
	private String questionCode;


	public AnswerUI() {
	}

	
	public String getAnswerValue() {
		return this.answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	public String getQuestionCode() {
		return this.questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

}
