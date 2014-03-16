package com.genoma.mrpoll.client.UiBinder;

import java.util.LinkedList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.CheckBox;

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
		gender.addItem("man");
		gender.addItem("woman");
		
	}
	
	
	public Boolean isValidProtocolNo(String input){
		Boolean result = true;
		try{
			Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			result=false;
		}
		
		return result;
	}
	
	public void setAttribute(){
		service.getPatientFromSession(new AsyncCallback<Patient>() {
		
			public void onSuccess(Patient result) {
				protocolno.setText(result.getProtocolNo().toString());
				name.setText(result.getNamesurname());
				age.setValue(result.getAge().toString());
				gender.setTitle(result.getGender());
			}
		
			public void onFailure(Throwable caught) {	
			}
		});
		
	}
	
	
	@Override
	public List<Answer> getAnswers() {
		return null;
	}

	@Override
	public void updateUi(List<Answer> answers) {
		setAttribute();
		
	}
}
