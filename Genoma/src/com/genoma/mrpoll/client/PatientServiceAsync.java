package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Question;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PatientServiceAsync {

	void saveAnswer(Answer answer, AsyncCallback<Void> callback);
	void saveAllAnswers(List<Answer> answers, AsyncCallback<Void> callback);
	void getAllAnswers(AsyncCallback<List<Answer>> callback);


}
