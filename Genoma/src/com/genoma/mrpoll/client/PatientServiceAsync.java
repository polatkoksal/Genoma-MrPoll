package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Question;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PatientServiceAsync {

	void saveAnswer(Answer answer, AsyncCallback<Void> callback);
	void saveAnswers(List<Answer> answers, AsyncCallback<Void> callback);
	void getAnswers(AsyncCallback<List<Answer>> callback);
	void savePatientToSession(Patient patient, AsyncCallback<Void> callback);
	void saveVisitToSession(Visit visit, AsyncCallback<Void> callback);
	void savePatient(Patient patient, AsyncCallback<Boolean> callback);
	void createVisit(AsyncCallback<Void> callback);
	void getPatientFromSession(AsyncCallback<Patient> callback);
	void getUserFromSession(AsyncCallback<UserUI> callback);
	void getVisitFromSession(AsyncCallback<Visit> callback);
	void updatePatient(Patient patient, AsyncCallback<Boolean> callback);
	


}
