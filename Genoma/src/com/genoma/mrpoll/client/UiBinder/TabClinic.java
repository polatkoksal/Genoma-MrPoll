package com.genoma.mrpoll.client.UiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TabClinic extends Composite implements HasText {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);

	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	public TabClinic() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public TabClinic(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	

	public void setText(String text) {
		
	}

	public String getText() {
		return null;
	}

}
