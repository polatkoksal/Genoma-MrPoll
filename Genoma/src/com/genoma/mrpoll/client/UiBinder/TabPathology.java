package com.genoma.mrpoll.client.UiBinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class TabPathology extends Composite {

	private static TabPathologyUiBinder uiBinder = GWT
			.create(TabPathologyUiBinder.class);

	interface TabPathologyUiBinder extends UiBinder<Widget, TabPathology> {
	}

	public TabPathology() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
