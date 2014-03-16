package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;

public class TabPathology extends Composite implements Updater {

	private static TabPathologyUiBinder uiBinder = GWT
			.create(TabPathologyUiBinder.class);
	@UiField CheckBox r_nolesion;
	@UiField CheckBox l_nolesion;
	@UiField ListBox l_metastasis;

	interface TabPathologyUiBinder extends UiBinder<Widget, TabPathology> {
	}

	public TabPathology(State s) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void updateUi(List<Answer> answers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}
}
