package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.SearchResultData;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchVisit extends Composite {

	private static SearchVisitUiBinder uiBinder = GWT.create(SearchVisitUiBinder.class);
	
	PatientServiceAsync service = GWT.create(PatientService.class);
	
	@UiField Button search;
	@UiField TextBox searchtext;
	@UiField ListBox select;
	@UiField VerticalPanel panel;
	@UiField Button cancel;


	interface SearchVisitUiBinder extends UiBinder<Widget, SearchVisit> {
	}

	public SearchVisit() {
		initWidget(uiBinder.createAndBindUi(this));
		select.addItem("Ad");
		select.addItem("Protokol No");
		select.addItem("Histopatolojik Tanı");
		select.addItem("Tedavi Tipi");
	}

	
	@UiHandler("search")
	void onSearchClick(ClickEvent event) {
		
		service.searchVisit(select.getSelectedIndex(), searchtext.getText(), new AsyncCallback<List<SearchResultData>>() {

			@Override
			public void onSuccess(List<SearchResultData> result) {
				panel.clear();
				panel.setHeight((result.size())*(new SearchVisitResult(null).getOffsetHeight())+"");
				
				for (SearchResultData searchResultData : result){
					SearchVisitResult searchVisitResult = new SearchVisitResult(searchResultData);
					panel.add(searchVisitResult);
				}
				Window.alert("searchVisit onSucces!");
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("searchVisit error!");
				
			}

	
		});
		
	}
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
