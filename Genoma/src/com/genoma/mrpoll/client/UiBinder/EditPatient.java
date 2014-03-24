package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.VisitUI;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class EditPatient extends Composite {

	private static GeneralInfoUiBinder uiBinder = GWT
			.create(GeneralInfoUiBinder.class);

	PatientServiceAsync service = GWT.create(PatientService.class);

	interface GeneralInfoUiBinder extends UiBinder<Widget, EditPatient> {
	}

	@UiField
	Button back;
	@UiField
	Button forward;
	@UiField
	Button save;
	@UiField
	Button cancel;
	@UiField
	Label patientid;
	@UiField
	HorizontalPanel panel;

	
	TabPatientInfo tabPatientInfo;
	TabVisitInfo tabVisitInfo;
	TabClinic tabClinic;
	TabMammography tabMammography;
	TabUltrasonography tabUltrasonography;
	TabMRI tabMRI;
	TabPathology tabPathology;
	TabSecondVisit tabSecondVisit;
	TabSurgical tabSurgical;
	
	Updater tab;
	
	public EditPatient(State s) {

		initWidget(uiBinder.createAndBindUi(this));
		setPatientId();
		tabPatientInfo = new TabPatientInfo(s);
		this.repaint(s);

	}

	public void repaint(State s) {
		
		switch (s) {

		case TAB_PATIENT_INFO:
			if(tabPatientInfo == null) {
				tabPatientInfo = new TabPatientInfo(s);
			}
			tab = tabPatientInfo;
			pointTo(null, back);
			pointTo(State.TAB_VISIT, forward);
			break;

		case TAB_VISIT:
			if(tabVisitInfo == null) {
				tabVisitInfo = new TabVisitInfo(s);
			}
			tab = tabVisitInfo;
			pointTo(State.TAB_PATIENT_INFO, back);
			pointTo(State.TAB_CLINIC, forward);
			break;

		case TAB_CLINIC:
			if(tabClinic == null) {
				tabClinic = new TabClinic(s);
			}
			tab = tabClinic;
			pointTo(State.TAB_VISIT, back);
			pointTo(State.TAB_MAMMOGRAPHY, forward);
			break;

		case TAB_MAMMOGRAPHY:
			if(tabMammography == null) {
				tabMammography = new TabMammography(s);
			}
			tab = tabMammography;
			pointTo(State.TAB_CLINIC, back);
			pointTo(State.TAB_USG, forward);
			break;

		case TAB_USG:
			if(tabUltrasonography == null) {
				tabUltrasonography = new TabUltrasonography(s);
			}
			tab = tabUltrasonography;
			pointTo(State.TAB_MAMMOGRAPHY, back);
			pointTo(State.TAB_MRI, forward);
			break;

		case TAB_MRI:
			if(tabMRI == null) {
				tabMRI = new TabMRI(s);
			}
			tab = tabMRI;
			pointTo(State.TAB_USG, back);
			pointTo(State.TAB_PATHOLOGY, forward);
			break;

		case TAB_PATHOLOGY:
			if(tabPathology == null) {
				tabPathology = new TabPathology(s);
			}
			tab = tabPathology;
			pointTo(State.TAB_MRI, back);
			pointTo(State.TAB_SECOND, forward);
			break;

		case TAB_SECOND:
			if(tabSecondVisit == null) {
				tabSecondVisit = new TabSecondVisit(s);
			}
			tab = tabSecondVisit;
			pointTo(State.TAB_PATHOLOGY, back);
			pointTo(State.TAB_SURGICAL, forward);
			break;

		case TAB_SURGICAL:
			if(tabSurgical == null) {
				tabSurgical = new TabSurgical(s);
			}
			tab = tabSurgical;
			pointTo(State.TAB_SECOND, back);
			pointTo(null, forward);
			break;
		default:
			break;
		}
		
		
		Tabs tabs = new Tabs(s, tab);
		panel.clear();
		panel.add(tabs);
		panel.add((Widget) tab);
	}

	public void pointTo(State target, Button button) {

		if (target == null) 
		{
			button.setVisible(false);
		} 
		else 
		{
			button.setVisible(true);
			button.addClickHandler(new ClickHandler() {
				State target;

				public ClickHandler init(State state) {
					target = state;
					return this;
				}

				public void onClick(ClickEvent event) {
					MrPoll.repaint(target);
				}
			}.init(target));
		}
	}

	public void setPatientId() {
		service.getPatientFromSession(new AsyncCallback<PatientUI>() {
			@Override
			public void onSuccess(PatientUI result) {
				patientid.setText(result.getProtocolNo());
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@UiHandler("save")
	void onSaveClick(ClickEvent event) {

		service.saveAnswersToSession(tab.getAnswersFromUi(),
				new AsyncCallback<Void>() {
					public void onSuccess(Void result) {

					}

					public void onFailure(Throwable caught) {
						Window.alert("target fails!!!");
					}
				});

		service.updatePatient(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {

					service.updateVisit(new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean result) {
							Window.alert("Kayıt Yapıldı!");
							MrPoll.repaint(State.MAIN_MENU);
						}

						@Override
						public void onFailure(Throwable caught) {

						}
					});
				} else {
					Window.alert("bu hasta daha önce oluşturuldu!!!");
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		Boolean b = Window.confirm("kaydetmeden cıkmak istiyormusunuz?");
		if (b) {
			service.close(new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					MrPoll.repaint(State.MAIN_MENU);
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("closing fail!!!");
				}

			});

		}

	}

}
