package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
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
		

		PatientUI patientUi = new PatientUI();
		patientUi.setProtocolNo(protocolno.getText());
		
		service.savePatient(patientUi, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if(!result){
					Boolean confirm = Window.confirm("kayıt bulundu! o kayıttan devam edilsin mi?");
					if(!confirm){
						service.createVisit(new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								MrPoll.repaint(State.TAB_PATIENT_INFO);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("createVisit Error!");
							}

						});	
					}
					else{
						MrPoll.repaint(State.TAB_PATIENT_INFO);
					}
				}
				else{
					MrPoll.repaint(State.TAB_PATIENT_INFO);
				}
				
				
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("createPatient Error!");
			}
		});
		
	}
}
