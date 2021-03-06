package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
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

	EditVisitData editVisitData = null;

	private static GeneralInfoUiBinder uiBinder = GWT
			.create(GeneralInfoUiBinder.class);

	PatientServiceAsync service = GWT.create(PatientService.class);

	interface GeneralInfoUiBinder extends UiBinder<Widget, EditPatient> {
	}

	@UiField
	Button back;
	HandlerRegistration backHandler = null;
	@UiField
	Button forward;
	HandlerRegistration forwardHandler = null;
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

	public EditPatient(EditVisitData result) {
		initWidget(uiBinder.createAndBindUi(this));
		editVisitData = result;
		// patientid.setText(result.getPatient().getProtocolNo());
		// ???????????????
		tabPatientInfo = new TabPatientInfo(result.getPatient());
		tabVisitInfo = new TabVisitInfo(result.getVisit());
		tabClinic = new TabClinic(result.getAnswers());
		tabMammography = new TabMammography(result.getAnswers());
		tabUltrasonography = new TabUltrasonography(result.getAnswers());
		tabMRI = new TabMRI(result.getAnswers());
		tabPathology = new TabPathology(result.getAnswers());
		tabSecondVisit = new TabSecondVisit(result.getAnswers());
		tabSurgical = new TabSurgical(result.getAnswers());
	}

	public void repaint(State s) {

		Widget tab = null;

		if (backHandler != null) {
			backHandler.removeHandler();
		}
		if (forwardHandler != null) {
			forwardHandler.removeHandler();
		}

		switch (s) {

		case TAB_PATIENT_INFO:
			tab = tabPatientInfo;
			backHandler = pointTo(null, back);
			forwardHandler = pointTo(State.TAB_VISIT, forward);
			break;

		case TAB_VISIT:
			tab = tabVisitInfo;
			backHandler = pointTo(State.TAB_PATIENT_INFO, back);
			forwardHandler = pointTo(State.TAB_CLINIC, forward);
			break;

		case TAB_CLINIC:
			tab = tabClinic;
			backHandler = pointTo(State.TAB_VISIT, back);
			forwardHandler = pointTo(State.TAB_MAMMOGRAPHY, forward);
			break;

		case TAB_MAMMOGRAPHY:
			tab = tabMammography;
			backHandler = pointTo(State.TAB_CLINIC, back);
			forwardHandler = pointTo(State.TAB_USG, forward);
			break;

		case TAB_USG:
			tab = tabUltrasonography;
			backHandler = pointTo(State.TAB_MAMMOGRAPHY, back);
			forwardHandler = pointTo(State.TAB_MRI, forward);
			break;

		case TAB_MRI:
			tab = tabMRI;
			backHandler = pointTo(State.TAB_USG, back);
			forwardHandler = pointTo(State.TAB_PATHOLOGY, forward);
			break;

		case TAB_PATHOLOGY:
			tab = tabPathology;
			backHandler = pointTo(State.TAB_MRI, back);
			forwardHandler = pointTo(State.TAB_SECOND, forward);
			break;

		case TAB_SECOND:
			tab = tabSecondVisit;
			backHandler = pointTo(State.TAB_PATHOLOGY, back);
			forwardHandler = pointTo(State.TAB_SURGICAL, forward);
			break;

		case TAB_SURGICAL:
			tab = tabSurgical;
			backHandler = pointTo(State.TAB_SECOND, back);
			forwardHandler = pointTo(null, forward);
			break;
		default:
			break;
		}

		Tabs tabs = new Tabs(s);
		panel.clear();
		panel.add(tabs);
		panel.add(tab);
	}

	public HandlerRegistration pointTo(State target, Button button) {
		final EditPatient editPatient = this;
		HandlerRegistration result = null;
		if (target == null) {
			button.setVisible(false);
		} else {
			button.setVisible(true);
			result = button.addClickHandler(new ClickHandler() {
				State target;

				public ClickHandler init(State state) {
					target = state;
					return this;
				}

				public void onClick(ClickEvent event) {
					editPatient.repaint(target);
				}
			}.init(target));
		}
		return result;
	}

	@UiHandler("save")
	void onSaveClick(ClickEvent event) {

		setEnable(false);
		setEditVisitData();

		service.saveEditVisitData(editVisitData, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if (!result) {
					Window.alert("bu patient no daha önce oluşturuldu!");
					setEnable(true);
				} else {
					Window.alert("kayıt yapıldı");
					MrPoll.repaint(State.MAIN_MENU);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("save failure!!!");
				setEnable(true);
			}

		});

	}

	public void setEditVisitData() {

		PatientUI patientUI = editVisitData.getPatient();
		VisitUI visitUI = editVisitData.getVisit();
		List<AnswerUI> answersUI = editVisitData.getAnswers();

		patientUI.setProtocolNo(tabPatientInfo.protocolno.getText());
		patientUI.setNameSurname(tabPatientInfo.name.getText());
		patientUI.setGender(tabPatientInfo.gender.getSelectedIndex() + "");

		visitUI.setDate(tabVisitInfo.date.getValue());
		visitUI.setHospital(tabVisitInfo.hospital.getText());
		visitUI.setAge(tabVisitInfo.age.getText());
		visitUI.setEthic(tabVisitInfo.ethic.getValue());
		visitUI.setNote(tabVisitInfo.note.getText());

		answersUI.clear();
		answersUI.addAll(MrPoll.getAnswersFromUi(tabClinic.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabMammography.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabMRI.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabPathology.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabSecondVisit.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabSurgical.panel));
		answersUI.addAll(MrPoll.getAnswersFromUi(tabUltrasonography.panel));

		for (AnswerUI answerUI : answersUI) {
			if (answerUI.getAnswerValue() == null) {
				answersUI.remove(answerUI);
			}
		}

		editVisitData.setPatient(patientUI);
		editVisitData.setVisit(visitUI);
		editVisitData.setAnswers(answersUI);

	}

	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}

	public void setEnable(Boolean b) {
		save.setEnabled(b);
		cancel.setEnabled(b);
		forward.setEnabled(b);
		back.setEnabled(b);
	}

}
