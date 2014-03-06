package com.genoma.mrpoll.client.UiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class TabGeneralInfo extends Composite {

	private static PatientInfoUiBinder uiBinder = GWT.create(PatientInfoUiBinder.class);
	
	@UiField RadioButton women;
	@UiField RadioButton man;

	interface PatientInfoUiBinder extends UiBinder<Widget, TabGeneralInfo> {
		
	}

	public TabGeneralInfo() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public TabGeneralInfo(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}



}
