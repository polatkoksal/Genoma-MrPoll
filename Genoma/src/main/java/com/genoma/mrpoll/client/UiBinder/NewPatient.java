package com.genoma.mrpoll.client.UiBinder;

import java.util.ArrayList;
import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;


public class NewPatient extends Composite {
	
	UserServiceAsync userService= GWT.create(UserService.class);
	PatientServiceAsync service = GWT.create(PatientService.class);
	
	private static NewPatientUiBinder uiBinder = GWT.create(NewPatientUiBinder.class);
	
	@UiField TextBox protocolno;
	@UiField Button cancel;
	@UiField Button add;

	interface NewPatientUiBinder extends UiBinder<Widget, NewPatient> {
	}

	public NewPatient() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
	@UiHandler("add")
	void onAddClick(ClickEvent event) {
		
		if(!protocolno.getText().equals("")){
			
			service.getEditVisitData(null, new AsyncCallback<EditVisitData>() {

				@Override
				public void onSuccess(EditVisitData result) {
					
					if(result.getPatient().getId() == null)
					{
						MrPoll.editPatientPanel = new EditPatient(result);
					}
					else
					{
						Boolean b = Window.confirm("Kayıt bulundu! Devam etmek ister misiniz?");
						if(!b)
						{
							result.setVisit(new VisitUI());
							result.setAnswers(new ArrayList<AnswerUI>());
						}					
						MrPoll.editPatientPanel = new EditPatient(result);
					}				
					MrPoll.repaint(State.TAB_PATIENT_INFO);
					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("getProperties Error!!!");
					
				}

			});
			
		}
		else{
			Window.alert("Hatatlı Protokol No Girdiniz!!!");
		}
		
	}
}
