package com.genoma.mrpoll.client.UiBinder;

import java.util.Date;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class TabVisitInfo extends Composite {

	PatientServiceAsync service = GWT.create(PatientService.class);

	private static TabVisitInfoUiBinder uiBinder = GWT
			.create(TabVisitInfoUiBinder.class);

	@UiField
	DateBox date;
	@UiField
	TextBox hospital;
	@UiField
	CheckBox ethic;
	@UiField
	TextArea note;
	@UiField
	TextBox age;

	interface TabVisitInfoUiBinder extends UiBinder<Widget, TabVisitInfo> {
	}

	public TabVisitInfo(VisitUI visitUI) {
		initWidget(uiBinder.createAndBindUi(this));
		updateUI(visitUI);
	}

	public void updateUI(VisitUI visitUI) {
		if (visitUI.getDate() == null) {
			visitUI.setDate(new Date());
		}
		date.setValue(visitUI.getDate());
		age.setText(visitUI.getAge());
		hospital.setText(visitUI.getHospital());
		ethic.setValue(visitUI.getEthic());
		note.setText(visitUI.getNote());

	}
}
