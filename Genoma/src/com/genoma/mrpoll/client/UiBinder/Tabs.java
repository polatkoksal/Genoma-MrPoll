package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

public class Tabs extends Composite {

	private static TabsUiBinder uiBinder = GWT.create(TabsUiBinder.class);
	@UiField VerticalPanel panel;
	@UiField Anchor patientinfo;
	@UiField Anchor clinic;
	@UiField Anchor mammography;
	@UiField Anchor mri;
	@UiField Anchor second;
	@UiField Anchor usg;
	@UiField Anchor surgical;
	@UiField Anchor pathology;

	interface TabsUiBinder extends UiBinder<Widget, Tabs> {
	}

	public Tabs() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public Tabs(State s) {
		this();
		switch (s){
			case TAB_GENERAL_INFO:
				patientinfo.setStyleName("important");
				break;
			case TAB_CLINIC:
				clinic.setStyleName("important");
				break;
		}
		
	}
	
	@UiHandler("patientinfo")
	void onPatientinfoClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_GENERAL_INFO);
	}
	@UiHandler("clinic")
	void onClinicClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_CLINIC);
	}
	
	@UiHandler("mammography")
	void onMammographyClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_MAMMOGRAPHY);
	}
	@UiHandler("usg")
	void onUsgClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_USG);
	}
	@UiHandler("mri")
	void onMriClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_MRI);
	}
	@UiHandler("pathology")
	void onPathologyClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_PATHOLOGY);
	}
	@UiHandler("second")
	void onSecondClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_SECOND);
	}
	@UiHandler("surgical")
	void onSurgicalClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_SURGICAL);
	}
	
}
