package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;


public class Tabs extends Composite {
	Updater tab;

	private static TabsUiBinder uiBinder = GWT.create(TabsUiBinder.class);
	
	@UiField VerticalPanel panel;
	@UiField PushButton patientinfo;
	@UiField PushButton visit;
	@UiField PushButton clinic;
	@UiField PushButton mammography;
	@UiField PushButton mri;
	@UiField PushButton second;
	@UiField PushButton usg;
	@UiField PushButton surgical;
	@UiField PushButton pathology;
	

	interface TabsUiBinder extends UiBinder<Widget, Tabs> {
	}

	public Tabs() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public Tabs(State s, Updater tab) {
		this();
		this.tab=tab;

		switch (s){
			case TAB_PATIENT_INFO:
				patientinfo.setStyleName("important");
				break;
		
			case TAB_VISIT:
				visit.setStyleName("important");
				break;
			
			case TAB_CLINIC:
				clinic.setStyleName("important");
				break;
			
			case TAB_MAMMOGRAPHY:
				mammography.setStyleName("important");
				break;
			
			case TAB_USG:
				usg.setStyleName("important");
				break;
			
			case TAB_MRI:
				mri.setStyleName("important");
				break;
			
			case TAB_PATHOLOGY:
				pathology.setStyleName("important");
				break;
			
			case TAB_SECOND:
				second.setStyleName("important");
				break;
			
			case TAB_SURGICAL:
				surgical.setStyleName("important");
				break;
			default:
				break;
		}
		
	}
	
	private void updateSession(){
		PatientServiceAsync service= GWT.create(PatientService.class);
		service.saveAnswersToSession(tab.getAnswersFromUi(), new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
			}
			public void onFailure(Throwable caught) {
			}
		});
	}
	
	@UiHandler("patientinfo")
	void onPatientinfoClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_PATIENT_INFO);
	}
	
	@UiHandler("visit")
	void onVisitClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_VISIT);
	}
	
	@UiHandler("clinic")
	void onClinicClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_CLINIC);
	}
	
	@UiHandler("mammography")
	void onMammographyClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_MAMMOGRAPHY);
	}
	
	@UiHandler("usg")
	void onUsgClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_USG);
	}
	
	@UiHandler("mri")
	void onMriClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_MRI);
	}
	
	@UiHandler("pathology")
	void onPathologyClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_PATHOLOGY);
	}
	
	@UiHandler("second")
	void onSecondClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_SECOND);
	}
	
	@UiHandler("surgical")
	void onSurgicalClick(ClickEvent event) {
		updateSession();
		MrPoll.repaint(State.TAB_SURGICAL);
	}
	
	
}
