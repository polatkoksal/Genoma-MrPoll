package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.SearchResultData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class SearchVisitResult extends Composite {
	
	SearchResultData srcResultData;

	PatientServiceAsync service = GWT.create(PatientService.class);
	
	private static SearchVisitResultUiBinder uiBinder = GWT.create(SearchVisitResultUiBinder.class);
	
	@UiField Button edit;
	@UiField Button delete;
	@UiField Label protocolno;
	@UiField Label name;
	@UiField Label age;
	@UiField Label hospital;
	@UiField Label date;

	interface SearchVisitResultUiBinder extends UiBinder<Widget, SearchVisitResult> {
		
	}


	public SearchVisitResult(SearchResultData searchResultData) {
		initWidget(uiBinder.createAndBindUi(this));
		srcResultData = searchResultData;
		if(searchResultData != null){
			protocolno.setText(searchResultData.getProtocolNo());
			name.setText(searchResultData.getNamaSurname());
			age.setText(searchResultData.getAge());
			hospital.setText(searchResultData.getHospital());
			date.setText(searchResultData.getDate().toString());
		}
		
	}

	@UiHandler("edit")
	void onEditClick(ClickEvent event) {
		setEnable(false);
		service.getEditVisitData(srcResultData, new AsyncCallback<EditVisitData>() {
			
			@Override
			public void onSuccess(EditVisitData result) {
				MrPoll.setEditVisitData(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setEnable(true);
				Window.alert("error in editClick");
			}
		});
		
	}
	
	@UiHandler("delete")
	void onDeleteClick(ClickEvent event) {
		setEnable(false);
		service.deleteVisit(srcResultData, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("Visit deleted");
				MrPoll.repaint(State.MAIN_MENU);				
			}
			

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Delete Visit Error!");
				setEnable(true);
			}
		});
	}
	
	public void setEnable(Boolean b){
		edit.setEnabled(b);
		delete.setEnabled(b);
	}
}
