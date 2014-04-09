package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;
import static com.genoma.mrpoll.client.MrPoll.setAnswerOf;

import java.util.LinkedList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TabClinic extends Composite implements Updater {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);
	@UiField ListBox menopause_combo;
	@UiField CheckBox whining;
	@UiField CheckBox physicalfinding;
	@UiField CheckBox riskfactor;
	@UiField AbsolutePanel panel;

	
	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	PatientServiceAsync service= GWT.create(PatientService.class);
	
	public TabClinic(List<AnswerUI> answers) {
		initWidget(uiBinder.createAndBindUi(this));
		menopause_combo.addItem("Premenapoz");
		menopause_combo.addItem("Postmenapoz");
		updateUI(answers);	
	}

	

	public void updateUI(List<AnswerUI> answers) {
		for(AnswerUI answer : answers){
			for(Widget w: panel){
				if(w instanceof HasName && ((HasName) w).getName().equals(answer.getQuestionCode())){
					setAnswerOf((HasName)w, answer.getAnswerValue());
				}
			}
		}
	}
	
	
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result = new LinkedList<AnswerUI>();
		for(Widget w: panel){
			if(w instanceof HasName){
				result.add(returnAnswerOf((HasName)w));
			}
		}
		return result;
	}

	

}
