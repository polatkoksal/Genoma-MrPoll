package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.Container;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {
	
	void saveAnswersToSession(List<Answer> answers);
	List<Answer> getAnswersFromSession();
	void savePatientToSession(PatientUI patientUi);
	void saveVisitToSession(VisitUI visitUi);
	Boolean updateVisit();
	Boolean savePatient(PatientUI patientUi);
	void createVisit();
	//PatientUI getPatientFromSession();
	UserUI getUserFromSession();
	VisitUI getVisitFromSession();
	Boolean updatePatient();
	Boolean getVisitFromDB();
	List<Answer> mergeAnswers(List<Answer> target, List<Answer> list );
	void close();
	Container getProperties(String protocolNo);
}
