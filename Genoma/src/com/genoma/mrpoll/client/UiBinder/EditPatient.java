package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class EditPatient extends Composite {

	private static GeneralInfoUiBinder uiBinder = GWT.create(GeneralInfoUiBinder.class);

	interface GeneralInfoUiBinder extends UiBinder<Widget, EditPatient> {
	}
	
	public void pointTo(State target, Button button){
		if(target==null){
			button.setVisible(false);
		}
		else{
			button.addClickHandler(new ClickHandler() {
				State target;
				public ClickHandler init(State state){
					target=state;
					return this;
				}
				public void onClick(ClickEvent event) {
					PatientServiceAsync service= GWT.create(PatientService.class);
					service.saveAllAnswers(tab.getAllAnswers(), new AsyncCallback<Void>() {
						public void onSuccess(Void result) {
							MrPoll.repaint(target);
						}
						public void onFailure(Throwable caught) {
						}
					});
				}
			}.init(target));
		}
	}

	public EditPatient() {
		initWidget(uiBinder.createAndBindUi(this));
		panel.add(new Tabs());
	}
	Updater tab;

	public EditPatient(State s){
		this();
		PatientServiceAsync service= GWT.create(PatientService.class);
		switch(s){
			case TAB_GENERAL_INFO:
				tab=new TabGeneralInfo();
				pointTo(null, back);
				pointTo(State.TAB_CLINIC,forward);
				break;
			case TAB_CLINIC:
				tab=new TabClinic();
				pointTo(State.TAB_GENERAL_INFO,back);
				pointTo(State.TAB_MAMMOGRAPHY,forward);
				break;
			case TAB_MAMMOGRAPHY:
				tab=new TabMammography();
				pointTo(State.TAB_CLINIC,back);
				pointTo(State.TAB_USG,forward);
				break;
			case TAB_USG:
				tab=new TabUltrasonography();
				pointTo(State.TAB_MAMMOGRAPHY, back);
				pointTo(State.TAB_MRI, forward);
				break;
			case TAB_MRI:
				tab=new TabMRI();
				pointTo(State.TAB_USG,back);
				pointTo(State.TAB_PATHOLOGY,forward);
				break;
			case TAB_PATHOLOGY:
				tab=new TabPathology();
				pointTo(State.TAB_MRI,back);
				pointTo(State.TAB_SECOND,forward);
				break;
			case TAB_SECOND:
				tab=new TabSecondVisit();
				pointTo(State.TAB_PATHOLOGY,back);
				pointTo(State.TAB_SURGICAL,forward);
				break;
			case TAB_SURGICAL:
				tab=new TabSurgical();
				pointTo(State.TAB_SECOND, back);
				pointTo(null,forward);
				break;
			default:
				break;
		}
		panel.add((Widget)tab);
		service.getAllAnswers(new AsyncCallback<List<Answer>>() {
			public void onSuccess(List<Answer> result) {
				tab.update(result);
			}
			public void onFailure(Throwable caught) {
			}
		});
	}
	
	@UiField Button mainmenu;
	@UiField Button back;
	@UiField Button forward;
	@UiField Button save;
	@UiField Button cancel;
	@UiField Label patientid;
	@UiField HorizontalPanel panel;

	
	@UiHandler("mainmenu")
	void onMainmenuClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
