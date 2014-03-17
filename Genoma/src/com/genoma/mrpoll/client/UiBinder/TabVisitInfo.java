package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextArea;

public class TabVisitInfo extends Composite implements Updater{

	private static TabVisitInfoUiBinder uiBinder = GWT
			.create(TabVisitInfoUiBinder.class);
	
	@UiField TextBox date;
	@UiField TextBox hospital;
	@UiField CheckBox ethic;
	@UiField TextArea note;

	interface TabVisitInfoUiBinder extends UiBinder<Widget, TabVisitInfo> {
	}

	public TabVisitInfo(State s) {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public List<Answer> getAnswersFromUi() {
		// TODO Auto-generated method stub
		return null;
	}

}
