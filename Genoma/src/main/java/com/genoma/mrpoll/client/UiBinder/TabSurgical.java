package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;
import static com.genoma.mrpoll.client.MrPoll.setAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class TabSurgical extends Composite implements Updater {
	
	PatientServiceAsync service= GWT.create(PatientService.class);

	private static SurgicalUiBinder uiBinder = GWT.create(SurgicalUiBinder.class);
	@UiField CheckBox l_mrc;
	@UiField ListBox l_op;
	@UiField ListBox r_op;
	@UiField CheckBox r_mrc;
	@UiField ListBox reop;
	@UiField TextBox ref;
	@UiField AbsolutePanel panel;

	interface SurgicalUiBinder extends UiBinder<Widget, TabSurgical> {
	}

	public TabSurgical(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		updateUi(list);
	}


	public void updateUi(List<AnswerUI> answers){
		for(AnswerUI answer : answers){
			for(Widget w: panel){
				if(w instanceof HasName && ((HasName) w).getName().equals(answer.getQuestionCode())){
					setAnswerOf((HasName)w, answer.getAnswer());
				}
			}
		}
	}


	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result= new ArrayList<AnswerUI>();
		for(Widget w: panel){
			if(w instanceof HasName){
				result.add(returnAnswerOf((HasName)w));
			}
		}
		return result;
	}

}
