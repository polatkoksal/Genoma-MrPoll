package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;

public class TabPathology extends Composite implements Updater {
	
	PatientServiceAsync service= GWT.create(PatientService.class);

	private static TabPathologyUiBinder uiBinder = GWT
			.create(TabPathologyUiBinder.class);
	@UiField CheckBox r_nolesion;
	@UiField CheckBox l_nolesion;
	@UiField ListBox l_metastasis;
	@UiField ListBox r_metastasis;
	@UiField ListBox r_lymphnode;
	@UiField ListBox l_lymphnode;
	@UiField ListBox r_her2;
	@UiField ListBox l_her2;
	@UiField ListBox r_pgr;
	@UiField ListBox l_pgr;
	@UiField ListBox r_er;
	@UiField ListBox l_er;
	@UiField ListBox r_stage;
	@UiField ListBox l_stage;
	@UiField ListBox r_hpdiag;
	@UiField ListBox l_hpdiag;

	interface TabPathologyUiBinder extends UiBinder<Widget, TabPathology> {
	}

	public TabPathology(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nolesion.setValue(true);
		l_nolesion.setValue(true);
		onR_nolesionClick(null);
		onL_nolesionClick(null);
//		updateUi();
	}

	public void updateUi(){
		/*service.getAnswersFromSession(new AsyncCallback<List<Answer>>() {
			public void onSuccess(List<Answer> result) {
				for(Answer answer : result){
					switch (answer.getBelongsQuestionId()){
						case 500:	r_nolesion.setValue(Boolean.parseBoolean(answer.getAnswer()));				break;
						case 501:	r_hpdiag.setSelectedIndex(Integer.parseInt(answer.getAnswer()));			break;
						case 502:	r_stage.setSelectedIndex(Integer.parseInt(answer.getAnswer()));				break;
						case 503:	r_er.setSelectedIndex(Integer.parseInt(answer.getAnswer()));					break;
						case 504:	r_pgr.setSelectedIndex(Integer.parseInt(answer.getAnswer()));					break;
						case 505:	r_her2.setSelectedIndex(Integer.parseInt(answer.getAnswer()));				break;
						case 506:	r_lymphnode.setSelectedIndex(Integer.parseInt(answer.getAnswer()));		break;
						case 507:	r_metastasis.setSelectedIndex(Integer.parseInt(answer.getAnswer()));	break;
						case 510:	r_nolesion.setValue(Boolean.parseBoolean(answer.getAnswer()));				break;
						case 511:	r_hpdiag.setSelectedIndex(Integer.parseInt(answer.getAnswer()));			break;
						case 512:	r_stage.setSelectedIndex(Integer.parseInt(answer.getAnswer()));				break;
						case 513:	r_er.setSelectedIndex(Integer.parseInt(answer.getAnswer()));					break;
						case 514:	r_pgr.setSelectedIndex(Integer.parseInt(answer.getAnswer()));					break;
						case 515:	r_her2.setSelectedIndex(Integer.parseInt(answer.getAnswer()));				break;
						case 516:	r_lymphnode.setSelectedIndex(Integer.parseInt(answer.getAnswer()));		break;
						case 517:	r_metastasis.setSelectedIndex(Integer.parseInt(answer.getAnswer()));	break;
					}
				}
			}
			public void onFailure(Throwable caught) {
			}
		});*/
	}


	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result= new ArrayList<AnswerUI>();
//		result.add(returnAnswerOf(500, r_nolesion));
//		result.add(returnAnswerOf(501, r_hpdiag));
//		result.add(returnAnswerOf(502, r_stage));
//		result.add(returnAnswerOf(503, r_er));
//		result.add(returnAnswerOf(504, r_pgr));
//		result.add(returnAnswerOf(505, r_her2));
//		result.add(returnAnswerOf(506, r_lymphnode));
//		result.add(returnAnswerOf(507, r_metastasis));
//		result.add(returnAnswerOf(510, l_nolesion));
//		result.add(returnAnswerOf(511, l_hpdiag));
//		result.add(returnAnswerOf(512, l_stage));
//		result.add(returnAnswerOf(513, l_er));
//		result.add(returnAnswerOf(514, l_pgr));
//		result.add(returnAnswerOf(515, l_her2));
//		result.add(returnAnswerOf(516, l_lymphnode));
//		result.add(returnAnswerOf(517, l_metastasis));
		return result;
	}
	@UiHandler("r_nolesion")
	void onR_nolesionClick(ClickEvent event) {
		Boolean lockStatus = !r_nolesion.getValue();
		r_hpdiag.setEnabled(lockStatus);
		r_stage.setEnabled(lockStatus);
		r_er.setEnabled(lockStatus);
		r_pgr.setEnabled(lockStatus);
		r_her2.setEnabled(lockStatus);
		r_lymphnode.setEnabled(lockStatus);
		r_metastasis.setEnabled(lockStatus);
	}
	@UiHandler("l_nolesion")
	void onL_nolesionClick(ClickEvent event) {
		Boolean lockStatus = !l_nolesion.getValue();
		l_hpdiag.setEnabled(lockStatus);
		l_stage.setEnabled(lockStatus);
		l_er.setEnabled(lockStatus);
		l_pgr.setEnabled(lockStatus);
		l_her2.setEnabled(lockStatus);
		l_lymphnode.setEnabled(lockStatus);
		l_metastasis.setEnabled(lockStatus);
	}
}
