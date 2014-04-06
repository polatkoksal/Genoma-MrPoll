package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;
import static com.genoma.mrpoll.client.MrPoll.setAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

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
	@UiField TextBox reop_other;
	@UiField Label reop_label;
	@UiField Label ref_label;
	@UiField Label reop_other_label;

	interface SurgicalUiBinder extends UiBinder<Widget, TabSurgical> {
	}

	public TabSurgical(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		l_op.addItem("Proflaktik mastektomi");
		l_op.addItem("Primer mastektomi");
		l_op.addItem("Meme koruyucu cerrahi");
		l_op.addItem("Pozitif cerrahi sınır nedeniyle re-operasyon");
		l_op.addItem("Geniş lokal eksizyon"); 
		l_op.addItem("Mastektomiye dönüş");
		l_op.addItem("MKC sonrası ipsilateral rekürrens nedeniyle sekonder mastektomi");
		r_op.addItem("Proflaktik mastektomi");
		r_op.addItem("Primer mastektomi");
		r_op.addItem("Meme koruyucu cerrahi");
		r_op.addItem("Pozitif cerrahi sınır nedeniyle re-operasyon");
		r_op.addItem("Geniş lokal eksizyon"); 
		r_op.addItem("Mastektomiye dönüş");
		r_op.addItem("MKC sonrası ipsilateral rekürrens nedeniyle sekonder mastektomi");
		reop.addItem("1mm");
		reop.addItem("2mm");
		reop.addItem("3mm");
		reop.addItem("Diğer");
		updateUi(list);
		onReopChange(null);
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

	@UiHandler("reop")
	void onReopChange(ChangeEvent event) {
		Boolean isLast = reop.getSelectedIndex()==3;
		reop_other.setVisible(isLast);
		reop_other_label.setVisible(isLast);
		reop_other.setEnabled(isLast);
	}
}
