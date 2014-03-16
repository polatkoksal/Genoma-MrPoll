package com.genoma.mrpoll.client.UiBinder;

import java.util.LinkedList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TabBar;

public class TabClinic extends Composite implements Updater {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);
	@UiField ListBox menopause_combo;
	@UiField CheckBox whining;
	@UiField CheckBox physicalfinding;
	@UiField CheckBox riskfactor;

	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	PatientServiceAsync service= GWT.create(PatientService.class);
	
	public TabClinic(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		menopause_combo.addItem("Premenapoz");
		menopause_combo.addItem("Postmenapoz");
	}

	@Override
	public void updateUi(List<Answer> answers) {
		for(Answer answer:answers){
			if(answer.getBelongsQuestionId()==21){
				for(int i=0; i<menopause_combo.getItemCount();i++){
					if(answer.getAnswer().equals(menopause_combo.getItemText(i))){
						menopause_combo.setSelectedIndex(i);
						break;
					}
				}
			}//break goes here
			if(answer.getBelongsQuestionId()==22){
				whining.setValue(Boolean.parseBoolean(answer.getAnswer()));
			}
			if(answer.getBelongsQuestionId()==23){
				riskfactor.setValue(Boolean.parseBoolean(answer.getAnswer()));
			}
			if(answer.getBelongsQuestionId()==24){
				physicalfinding.setValue(Boolean.parseBoolean(answer.getAnswer()));
			}
		}
		
	}
	
	
	@Override
	public List<Answer> getAnswers() {
		List<Answer> result = new LinkedList<Answer>();
		Answer ats;
		ats=new Answer();
		ats.setBelongsQuestionId(21);
		ats.setAnswer(menopause_combo.getItemText(menopause_combo.getSelectedIndex()));
		result.add(ats);
		ats=new Answer();
		ats.setBelongsQuestionId(22);
		ats.setAnswer(whining.getValue().toString());
		result.add(ats);
		ats=new Answer();
		ats.setBelongsQuestionId(23);
		ats.setAnswer(riskfactor.getValue().toString());
		result.add(ats);
		ats=new Answer();
		ats.setBelongsQuestionId(24);
		ats.setAnswer(physicalfinding.getValue().toString());
		result.add(ats);
		
		return result;
	}

	

}
