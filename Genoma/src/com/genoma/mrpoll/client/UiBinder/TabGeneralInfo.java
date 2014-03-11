package com.genoma.mrpoll.client.UiBinder;

import java.util.LinkedList;
import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

public class TabGeneralInfo extends Composite implements Updater {

	private static PatientInfoUiBinder uiBinder = GWT.create(PatientInfoUiBinder.class);
	
	@UiField RadioButton woman;
	@UiField RadioButton man;
	@UiField TextBox protocolno;
	@UiField TextBox name;
	@UiField TextBox age;
	@UiField DateBox date;
	@UiField TextBox hospital;

	interface PatientInfoUiBinder extends UiBinder<Widget, TabGeneralInfo> {
		
	}

	public TabGeneralInfo() {
		initWidget(uiBinder.createAndBindUi(this));
		setActive(false);
	}
	
//	@UiHandler("protocolno")
//	void onProtocolnoKeyPress(KeyPressEvent event) {
//		if(isValidProtocolNo(protocolno.getText())){
//			setActive(true);
//		}
//		else{
//			setActive(false);
//		}
//	}
	
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
	
	public void setActive(Boolean b){
		woman.setEnabled(b);
		man.setEnabled(b);
		name.setEnabled(b);
		age.setEnabled(b);
		date.setEnabled(b);
		hospital.setEnabled(b);
	}
	
	@UiHandler("protocolno")
	void onProtocolnoKeyUp(KeyUpEvent event) {
		if(isValidProtocolNo(protocolno.getText())){
			setActive(true);
		}
		else{
			setActive(false);
		}
	}


	@Override
	public List<Answer> getAllAnswers() {
//		List<Answer> result = new LinkedList<Answer>();
//		Answer ats;
//		ats=new Answer();
//		ats.setBelongsQuestionId(10);
//		ats.setAnswer(name.getText());
//		result.add(ats);
//		ats=new Answer();
//		ats.setBelongsQuestionId(11);
//		ats.setAnswer();
		return null;
	}

	@Override
	public void update(List<Answer> answers) {
		// TODO Auto-generated method stub
		
	}
}
