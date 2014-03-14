package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Visit;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {
	void saveAnswer(Answer answer);
	void saveAllAnswers(List<Answer> answers);
	List<Answer> getAllAnswers();
	void savePatientToSession(Patient patient);
	void saveVisitToSession(Visit visit);
}
