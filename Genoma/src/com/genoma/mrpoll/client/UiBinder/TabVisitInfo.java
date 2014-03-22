package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DateBox;

public class TabVisitInfo extends Composite implements Updater{

	PatientServiceAsync service= GWT.create(PatientService.class);

	
	private static TabVisitInfoUiBinder uiBinder = GWT.create(TabVisitInfoUiBinder.class);
	
	@UiField DateBox date;
	@UiField TextBox hospital;
	@UiField CheckBox ethic;
	@UiField TextArea note;

	interface TabVisitInfoUiBinder extends UiBinder<Widget, TabVisitInfo> {
	}

	public TabVisitInfo(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		updateUi();
	}

	public void updateUi(){
		service.getVisitFromSession(new AsyncCallback<VisitUI>() {
			
			@Override
			public void onSuccess(VisitUI result) {
				date.setValue(result.getDate());
				hospital.setText(result.getHospital());
				note.setText(result.getNote());
				ethic.setValue(result.getEthic());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}

	@Override
	public List<Answer> getAnswersFromUi() {
		
		VisitUI visitUi = new VisitUI();
		visitUi.setDate(date.getValue());
		visitUi.setHospital(hospital.getText());
		visitUi.setNote(note.getText());
		visitUi.setEthic(ethic.getValue());
		
		service.saveVisitToSession(visitUi, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				
			}
			
			
			@Override
			public void onFailure(Throwable caught) {
				
			}

			
		});
		
		return null;
	}

}
