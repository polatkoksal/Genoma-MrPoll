package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TabClinic extends Composite {

	private static ClinicUiBinder uiBinder = GWT.create(ClinicUiBinder.class);
	@UiField
	ListBox menopause_combo;
	@UiField
	CheckBox whining;
	@UiField
	CheckBox physicalfinding;
	@UiField
	CheckBox riskfactor;
	@UiField
	AbsolutePanel panel;

	interface ClinicUiBinder extends UiBinder<Widget, TabClinic> {
	}

	PatientServiceAsync service = GWT.create(PatientService.class);

	public TabClinic(List<AnswerUI> answers) {
		initWidget(uiBinder.createAndBindUi(this));
		menopause_combo.addItem("Premenapoz");
		menopause_combo.addItem("Postmenapoz");
		MrPoll.updateUi(panel, answers);
	}

}
