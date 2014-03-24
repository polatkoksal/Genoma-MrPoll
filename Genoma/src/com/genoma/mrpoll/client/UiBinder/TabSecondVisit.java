package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Question;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TabSecondVisit extends Composite implements Updater {

	private static SecondUiBinder uiBinder = GWT.create(SecondUiBinder.class);
	@UiField
	CheckBox r_mri_biopsy;
	@UiField
	ListBox r_mri_biopsy_diagnosis;
	@UiField
	CheckBox r_usg;
	@UiField
	CheckBox r_mass;
	@UiField
	CheckBox r_nonmass;
	@UiField
	CheckBox r_usg_biopsy;
	@UiField
	ListBox r_usg_biopsy_diagnosis;
	@UiField
	CheckBox l_mri_biopsy;
	@UiField
	ListBox l_mri_biopsy_diagnosis;
	@UiField
	CheckBox l_usg;
	@UiField
	CheckBox l_mass;
	@UiField
	CheckBox l_nonmass;
	@UiField
	CheckBox l_usg_biopsy;
	@UiField
	ListBox l_usg_biopsy_diagnosis;
	@UiField
	Label l_usg_biopsy_diagnosis_label;
	@UiField
	Label r_usg_biopsy_diagnosis_label;

	interface SecondUiBinder extends UiBinder<Widget, TabSecondVisit> {
	}

	public TabSecondVisit(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		onR_mri_biopsyClick(null);
		onL_mri_biopsyClick(null);
		onR_usgClick(null);
		onL_usgClick(null);
		r_mri_biopsy_diagnosis.addItem("Duktal");
		r_mri_biopsy_diagnosis.addItem("Lobüler");
		r_mri_biopsy_diagnosis.addItem("Mikst");
		r_mri_biopsy_diagnosis.addItem("Diğer");
		r_usg_biopsy_diagnosis.addItem("Duktal");
		r_usg_biopsy_diagnosis.addItem("Lobüler");
		r_usg_biopsy_diagnosis.addItem("Mikst");
		r_usg_biopsy_diagnosis.addItem("Diğer");
		l_mri_biopsy_diagnosis.addItem("Duktal");
		l_mri_biopsy_diagnosis.addItem("Lobüler");
		l_mri_biopsy_diagnosis.addItem("Mikst");
		l_mri_biopsy_diagnosis.addItem("Diğer");
		l_usg_biopsy_diagnosis.addItem("Duktal");
		l_usg_biopsy_diagnosis.addItem("Lobüler");
		l_usg_biopsy_diagnosis.addItem("Mikst");
		l_usg_biopsy_diagnosis.addItem("Diğer");
	}

	

	@UiHandler("r_usg_biopsy")
	void onR_usg_biopsyClick(ClickEvent event) {
		Boolean lockStatus = r_usg_biopsy.isEnabled()
				&& r_usg_biopsy.getValue();
		r_usg_biopsy_diagnosis.setEnabled(lockStatus);
	}

	@UiHandler("r_usg")
	void onR_usgClick(ClickEvent event) {
		Boolean lockStatus = r_usg.isEnabled() && r_usg.getValue();
		r_mass.setEnabled(lockStatus);
		r_nonmass.setEnabled(lockStatus);
		r_usg_biopsy.setEnabled(lockStatus);
		onR_usg_biopsyClick(null);
	}

	@UiHandler("l_usg_biopsy")
	void onL_usg_biopsyClick(ClickEvent event) {
		Boolean lockStatus = l_usg_biopsy.isEnabled()
				&& l_usg_biopsy.getValue();
		l_usg_biopsy_diagnosis.setEnabled(lockStatus);
	}

	@UiHandler("r_mri_biopsy")
	void onR_mri_biopsyClick(ClickEvent event) {
		Boolean lockStatus = r_mri_biopsy.isEnabled()
				&& r_mri_biopsy.getValue();
		r_mri_biopsy_diagnosis.setEnabled(lockStatus);
	}

	@UiHandler("l_mri_biopsy")
	void onL_mri_biopsyClick(ClickEvent event) {
		Boolean lockStatus = l_mri_biopsy.isEnabled()
				&& l_mri_biopsy.getValue();
		l_mri_biopsy_diagnosis.setEnabled(lockStatus);
	}

	@UiHandler("l_usg")
	void onL_usgClick(ClickEvent event) {
		Boolean lockStatus = l_usg.isEnabled() && l_usg.getValue();
		l_mass.setEnabled(lockStatus);
		l_nonmass.setEnabled(lockStatus);
		l_usg_biopsy.setEnabled(lockStatus);
		onL_usg_biopsyClick(null);
	}

	@Override
	public List<Answer> getAnswersFromUi() {
		List<Answer> result = new ArrayList<Answer>();
		result.add(returnAnswerOf(600, r_mri_biopsy));
		result.add(returnAnswerOf(601, r_mri_biopsy_diagnosis));
		result.add(returnAnswerOf(610, r_usg));
		result.add(returnAnswerOf(611, r_mass));
		result.add(returnAnswerOf(612, r_nonmass));
		result.add(returnAnswerOf(613, r_usg_biopsy));
		result.add(returnAnswerOf(615, r_usg_biopsy_diagnosis));
		result.add(returnAnswerOf(650, l_mri_biopsy));
		result.add(returnAnswerOf(651, l_mri_biopsy_diagnosis));
		result.add(returnAnswerOf(660, l_usg));
		result.add(returnAnswerOf(661, l_mass));
		result.add(returnAnswerOf(662, l_nonmass));
		result.add(returnAnswerOf(663, l_usg_biopsy));
		result.add(returnAnswerOf(665, l_usg_biopsy_diagnosis));
		return result;
	}
}
