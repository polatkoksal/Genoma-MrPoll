package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.SearchResultData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("patient")
public interface PatientService extends RemoteService {
	
//	void saveAnswersToSession(List<Answer> answers);
//	List<Answer> getAnswersFromSession();
//	void savePatientToSession(PatientUI patientUi);
//	void saveVisitToSession(VisitUI visitUi);
//	Boolean updateVisit();
//	Boolean savePatient(PatientUI patientUi);
//	void createVisit();
//	PatientUI getPatientFromSession();
//	UserUI getUserFromSession();
//	VisitUI getVisitFromSession();
//	Boolean updatePatient();
//	Boolean getVisitFromDB();
//	List<Answer> mergeAnswers(List<Answer> target, List<Answer> list );
//	void close();
	EditVisitData getEditVisitData(SearchResultData searchResultData);
	Boolean saveEditVisitData(EditVisitData container);
	PatientUI getPatientFromDB(String protocolNo);
	List<SearchResultData> searchVisit(Integer selection, String search);
	Boolean deleteVisit(SearchResultData searchResultData);
}
