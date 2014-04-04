package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PatientServiceAsync {

//	void saveAnswersToSession(List<Answer> answers, AsyncCallback<Void> callback);
//	void getAnswersFromSession(AsyncCallback<List<Answer>> callback);
//	void savePatientToSession(PatientUI patientUi, AsyncCallback<Void> callback);
//	void saveVisitToSession(VisitUI visitUi, AsyncCallback<Void> callback);
//	void savePatient(PatientUI patientUi, AsyncCallback<Boolean> callback);
//	void createVisit(AsyncCallback<Void> callback);
//	void getPatientFromSession(AsyncCallback<PatientUI> callback);
//	void getUserFromSession(AsyncCallback<UserUI> callback);
//	void getVisitFromSession(AsyncCallback<VisitUI> callback);
//	void updatePatient(AsyncCallback<Boolean> callback);
//	void updateVisit(AsyncCallback<Boolean> callback);
//	void getVisitFromDB(AsyncCallback<Boolean> callback);
//	void mergeAnswers(List<Answer> target, List<Answer> list, AsyncCallback<List<Answer>> callback);
//	void close(AsyncCallback<Void> callback);
	void getEditVisitData(String protocolNo, AsyncCallback<EditVisitData> callback);
	void saveEditVisitData(EditVisitData container, AsyncCallback<Boolean> callback);
	void getPatientFromDB(String protocolNo, AsyncCallback<PatientUI> callback);
	void searchVisit(Integer selection, String search, AsyncCallback<List<EditVisitData>> callback);
	


}
