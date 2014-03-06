package com.genoma.mrpoll.client.UiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TabSurgical extends Composite {

	private static SurgicalUiBinder uiBinder = GWT.create(SurgicalUiBinder.class);

	interface SurgicalUiBinder extends UiBinder<Widget, TabSurgical> {
	}

	public TabSurgical() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
