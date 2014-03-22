package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Question;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PatientServiceAsync {

	void saveAnswersToSession(List<Answer> answers, AsyncCallback<Void> callback);
	void getAnswersFromSession(AsyncCallback<List<Answer>> callback);
	void savePatientToSession(PatientUI patientUi, AsyncCallback<Void> callback);
	void saveVisitToSession(VisitUI visitUi, AsyncCallback<Void> callback);
	void savePatient(PatientUI patientUi, AsyncCallback<Boolean> callback);
	void createVisit(AsyncCallback<Void> callback);
	void getPatientFromSession(AsyncCallback<PatientUI> callback);
	void getUserFromSession(AsyncCallback<UserUI> callback);
	void getVisitFromSession(AsyncCallback<VisitUI> callback);
	void updatePatient(PatientUI patientUi, AsyncCallback<Boolean> callback);
	void updateVisit(VisitUI visitUi, AsyncCallback<Boolean> callback);
	void getVisitFromDB(AsyncCallback<Boolean> callback);
	void mergeAnswers(List<Answer> target, List<Answer> list, AsyncCallback<List<Answer>> callback);
	


}
