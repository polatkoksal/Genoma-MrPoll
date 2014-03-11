package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TabSurgical extends Composite implements Updater {

	private static SurgicalUiBinder uiBinder = GWT.create(SurgicalUiBinder.class);

	interface SurgicalUiBinder extends UiBinder<Widget, TabSurgical> {
	}

	public TabSurgical() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void update(List<Answer> answers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getAllAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

}
