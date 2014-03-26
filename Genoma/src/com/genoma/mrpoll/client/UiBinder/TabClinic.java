package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.LinkedList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TabClinic extends Composite implements Updater {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);
	@UiField ListBox menopause_combo;
	@UiField CheckBox whining;
	@UiField CheckBox physicalfinding;
	@UiField CheckBox riskfactor;

	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	PatientServiceAsync service= GWT.create(PatientService.class);
	
	public TabClinic(List<AnswerUI> answers) {
		initWidget(uiBinder.createAndBindUi(this));
		menopause_combo.addItem("Premenapoz");
		menopause_combo.addItem("Postmenapoz");
		updateUI(answers);
		
//		Map<String, Object> fields = new HashMap<String, Object>();
//		
//		fields.put("0301", menopause_combo);
//		fields.put("0302", whining);
//		fields.put("0303", physicalfinding);
//		fields.put("0304", riskfactor);
		
	}

	
//	void saveButton()
//	{
//		Set keys = fields.keySet();
//		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
//			String questionCode = (String) iterator.next();
//			Object uiField = fields.get(questionCode);
//			String value = null;
//			if (uiField instanceof CheckBox) {
//				// your code
//				value = ((CheckBox) uiField).getValue().toString();
//			}
//			if (uiField instanceof ListBox) {
//				// your code
//				value = ((ListBox) uiField).getValue(((ListBox) uiField)
//						.getSelectedIndex());
//			}
//			Answer answer = new Answer();
//			Question q = null; // questionCode a sahip soruyu al. Ornek 0201.
//			answer.setBelongsQuestionId(belongsQuestionId);
//			answer.setVisit;
//			answer.setValue(value);
//			em.persist;
//  
//	}
	

	public void updateUI(List<AnswerUI> answers) {
		for(AnswerUI answer : answers){
			switch (answer.getBelongsQuestionId()){
				case 21:	menopause_combo.setSelectedIndex(Integer.parseInt(answer.getAnswer()));		break;
				case 22:	whining.setValue(Boolean.parseBoolean(answer.getAnswer()));					break;
				case 23:	riskfactor.setValue(Boolean.parseBoolean(answer.getAnswer()));				break;
				case 24:	physicalfinding.setValue(Boolean.parseBoolean(answer.getAnswer()));			break;
			}
		}
		
	}
	
	
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result = new LinkedList<AnswerUI>();
		result.add(returnAnswerOf(21, menopause_combo));
		result.add(returnAnswerOf(22, whining));
		result.add(returnAnswerOf(23, riskfactor));
		result.add(returnAnswerOf(24, physicalfinding));
		return result;
	}

	

}
