package com.genoma.mrpoll.client.UiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;

public class TabClinic extends Composite {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);
	@UiField ListBox menopause_combo;

	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	public TabClinic() {
		initWidget(uiBinder.createAndBindUi(this));
		menopause_combo.addItem("Premenapoz");
		menopause_combo.addItem("Postmenapoz");
		//menopause_combo.getValue(menopause_combo.getSelectedIndex());
	}

}
