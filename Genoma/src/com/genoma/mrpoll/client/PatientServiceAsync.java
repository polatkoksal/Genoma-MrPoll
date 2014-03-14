package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Question;
import com.genoma.mrpoll.domain.Visit;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PatientServiceAsync {

	void saveAnswer(Answer answer, AsyncCallback<Void> callback);
	void saveAllAnswers(List<Answer> answers, AsyncCallback<Void> callback);
	void getAllAnswers(AsyncCallback<List<Answer>> callback);
	void savePatientToSession(Patient patient, AsyncCallback<Void> callback);
	void saveVisitToSession(Visit visit, AsyncCallback<Void> callback);


}
