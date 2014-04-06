package com.genoma.mrpoll.client.UiBinder;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;
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
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.dom.client.BlurEvent;

public class TabPatientInfo extends Composite implements Updater {
	
	PatientServiceAsync service= GWT.create(PatientService.class);

	private static PatientInfoUiBinder uiBinder = GWT.create(PatientInfoUiBinder.class);
	
	@UiField TextBox protocolno;
	@UiField TextBox name;
	@UiField ListBox gender;

	interface PatientInfoUiBinder extends UiBinder<Widget, TabPatientInfo> {
		
	}

	public TabPatientInfo(PatientUI patientUI) {
		initWidget(uiBinder.createAndBindUi(this));
		gender.addItem("Kadın");
		gender.addItem("Erkek");
		updateUI(patientUI);
	}
	
	

	
	public void updateUI(PatientUI patientUI){
		if(patientUI.getProtocolNo() != null){
			protocolno.setText(patientUI.getProtocolNo());
		}
		name.setText(patientUI.getNameSurname());
		if(patientUI.getGender() != null){
			gender.setSelectedIndex(Integer.parseInt(patientUI.getGender()));
		}
			
	}

	@UiHandler("protocolno")
	void onProtocolnoBlur(BlurEvent event) {
		
		service.getPatientFromDB(protocolno.getText(), new AsyncCallback<PatientUI>() {

			@Override
			public void onSuccess(PatientUI result) {
				updateUI(result);
			}
			
			@Override
			public void onFailure(Throwable result) {
				Window.alert("onBlur Error!");
			}

		});
		
	}
	
	
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		
		/*PatientUI patientUi = new PatientUI();
		patientUi.setProtocolNo(protocolno.getText());
		patientUi.setNameSurname(name.getText());
		patientUi.setGender(gender.getSelectedIndex());
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

		});*/
		
		
		
		return null;
	}

	
	
}
