package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {
	void saveAnswer(Answer answer);
	void saveAnswers(List<Answer> answers);
	List<Answer> getAnswers();
	void savePatientToSession(Patient patient);
	void saveVisitToSession(Visit visit);
	Boolean savePatient(Patient patient);
	void createVisit();
	Patient getPatientFromSession();
	UserUI getUserFromSession();
	Visit getVisitFromSession();
	Boolean updatePatient(Patient patient);
}
