package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class TabSurgical extends Composite implements Updater {
	
	PatientServiceAsync service= GWT.create(PatientService.class);

	private static SurgicalUiBinder uiBinder = GWT.create(SurgicalUiBinder.class);
	@UiField CheckBox l_mrc;
	@UiField ListBox l_op;
	@UiField ListBox r_op;
	@UiField CheckBox r_mrc;
	@UiField ListBox reop;
	@UiField TextBox ref;

	interface SurgicalUiBinder extends UiBinder<Widget, TabSurgical> {
	}

	public TabSurgical(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		updateUi();
	}


	void updateUi() {
		service.getAnswersFromSession(new AsyncCallback<List<Answer>>() {
			public void onSuccess(List<Answer> result) {
				for(Answer answer : result){
					switch (answer.getBelongsQuestionId()){
						case 71: r_mrc.setValue(Boolean.parseBoolean(answer.getAnswer()));		break;
						case 72: r_op.setSelectedIndex(Integer.parseInt(answer.getAnswer()));	break;
						case 73: l_mrc.setValue(Boolean.parseBoolean(answer.getAnswer()));		break;
						case 74: l_op.setSelectedIndex(Integer.parseInt(answer.getAnswer()));	break;
						case 75: reop.setSelectedIndex(Integer.parseInt(answer.getAnswer()));	break;
						case 76: ref.setText(answer.getAnswer());															break;
					}
				}
			}
			public void onFailure(Throwable caught) {
			}
		});
	}


	@Override
	public List<Answer> getAnswersFromUi() {
		List <Answer> result = new ArrayList<Answer>();
		result.add(returnAnswerOf(71, r_mrc));
		result.add(returnAnswerOf(72, r_op));
		result.add(returnAnswerOf(73, l_mrc));
		result.add(returnAnswerOf(74, l_op));
		result.add(returnAnswerOf(75, reop));
		result.add(returnAnswerOf(76, ref));
		return result;
	}

}
