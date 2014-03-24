package com.genoma.mrpoll.client.UiBinder;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TabPatientInfo extends Composite implements Updater {
	
	PatientServiceAsync service= GWT.create(PatientService.class);

	private static PatientInfoUiBinder uiBinder = GWT.create(PatientInfoUiBinder.class);
	
	@UiField TextBox protocolno;
	@UiField TextBox name;
	@UiField TextBox age;
	@UiField ListBox gender;

	interface PatientInfoUiBinder extends UiBinder<Widget, TabPatientInfo> {
		
	}

	public TabPatientInfo(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		gender.addItem("Kadın");
		gender.addItem("Erkek");
		//updateUi();
	}
	
	

	
	public void updateUi(){
		service.getPatientFromSession(new AsyncCallback<PatientUI>() {
		
			public void onSuccess(PatientUI result) {
				protocolno.setText(result.getProtocolNo().toString());
				name.setText(result.getNameSurname());
				age.setValue(result.getAge().toString());
				gender.setTitle(result.getGender());
			}
		
			public void onFailure(Throwable caught) {	
			}
		});
		
	}
	
	
	@Override
	public List<Answer> getAnswersFromUi() {
		
		PatientUI patientUi = new PatientUI();
		patientUi.setProtocolNo(protocolno.getText());
		patientUi.setNameSurname(name.getText());
		patientUi.setGender(gender.getItemText(0));
		if(age.getText() != ""){
			patientUi.setAge(Integer.parseInt(age.getText()));
		}
		
		service.savePatientToSession(patientUi, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}

		});
		
		
		
		return null;
	}

}
