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
import com.genoma.mrpoll.uihelper.Container;
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
			
			service.getProperties(protocolno.getText(), new AsyncCallback<Container>() {

				@Override
				public void onSuccess(Container result) {
					
					if(result.getVisit().getDate() == null){	
						MrPoll.editPatientPanel = new EditPatient(result);
					}else{
						Boolean b = Window.confirm("Kayıt bulundu! Devam etmek ister misiniz?");
						if(!b){
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
			
			
			
			
			
			
			/*PatientUI patientUi = new PatientUI();
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
							service.getVisitFromDB(new AsyncCallback<Boolean>() {
	
								@Override
								public void onSuccess(Boolean result) {
									MrPoll.repaint(State.TAB_PATIENT_INFO);
								}
								
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("get hataaaa");
								}	
							});
							
						}
					}
					else{
						service.createVisit(new AsyncCallback<Void>() {
	
							@Override
							public void onSuccess(Void result) {
								MrPoll.repaint(State.TAB_PATIENT_INFO);
							}
							
							@Override
							public void onFailure(Throwable caught) {
							}
	
							
						});
						
					}
					
					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("createPatient Error!");
				}
			});*/
		}
		else{
			Window.alert("Hatatlı Protokol No Girdiniz!!!");
		}
		
	}
}
