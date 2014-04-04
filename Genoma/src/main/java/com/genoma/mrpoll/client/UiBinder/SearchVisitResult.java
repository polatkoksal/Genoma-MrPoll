package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class SearchVisitResult extends Composite {
	
	EditVisitData edVisitData;

	private static SearchVisitResultUiBinder uiBinder = GWT.create(SearchVisitResultUiBinder.class);
	
	@UiField Label protocolno;
	@UiField Button edit;
	@UiField Button delete;

	interface SearchVisitResultUiBinder extends UiBinder<Widget, SearchVisitResult> {
		
	}

	public SearchVisitResult(EditVisitData editVisitData) {
		initWidget(uiBinder.createAndBindUi(this));
		edVisitData = editVisitData;
	}

	@UiHandler("edit")
	void onEditClick(ClickEvent event) {
		Window.alert("onEditClick");
		MrPoll.setEditVisitData(edVisitData);
	}
	
	@UiHandler("delete")
	void onDeleteClick(ClickEvent event) {
	}
}
