package com.genoma.mrpoll.client.UiBinder;

import java.util.List;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TabMRI extends Composite {

	PatientServiceAsync service = GWT.create(PatientService.class);

	private static MammographyUiBinder uiBinder = GWT
			.create(MammographyUiBinder.class);
	@UiField
	CheckBox r_nofinding;
	@UiField
	CheckBox r_mass;
	@UiField
	TextBox r_lesionnumber;
	@UiField
	Label r_lesionnumber_label;
	@UiField
	ListBox r_lesionspread;
	@UiField
	Label r_lesionspread_label;
	@UiField
	TextBox r_lesionsize;
	@UiField
	Label r_lesionsize_label;
	@UiField
	CheckBox r_capsuleinvasion;
	@UiField
	CheckBox l_nofinding;
	@UiField
	CheckBox l_mass;
	@UiField
	TextBox l_lesionnumber;
	@UiField
	Label l_lesionnumber_label;
	@UiField
	ListBox l_lesionspread;
	@UiField
	Label l_lesionspread_label;
	@UiField
	TextBox l_lesionsize;
	@UiField
	Label l_lesionsize_label;
	@UiField
	CheckBox r_axillary;
	@UiField
	CheckBox l_axillary;
	@UiField
	TextBox r_lymphnodecount;
	@UiField
	TextBox r_largestnoderadius;
	@UiField
	Label r_lymphnodecount_label;
	@UiField
	Label r_largestnoderadius_label;
	@UiField
	CheckBox l_capsuleinvasion;
	@UiField
	TextBox l_lymphnodecount;
	@UiField
	TextBox l_largestnoderadius;
	@UiField
	Label l_lymphnodecount_label;
	@UiField
	Label l_largestnoderadius_label;
	@UiField
	CheckBox r_nonmassstain;
	@UiField
	CheckBox r_ductalstain;
	@UiField
	CheckBox r_focallesion;
	@UiField
	CheckBox l_nonmassstain;
	@UiField
	CheckBox l_ductalstain;
	@UiField
	CheckBox l_focallesion;
	@UiField
	AbsolutePanel panel;
	@UiField
	ListBox r_indication;
	@UiField
	ListBox l_indication;
	@UiField
	TextBox r_indication_other;
	@UiField
	TextBox l_indication_other;
	@UiField TextBox r_nonmassstain_desc;
	@UiField TextBox l_nonmassstain_desc;

	interface MammographyUiBinder extends UiBinder<Widget, TabMRI> {
	}

	public TabMRI(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		r_lesionspread.addItem("Multifokal");
		r_lesionspread.addItem("Multisentrik");
		l_lesionspread.addItem("Multifokal");
		l_lesionspread.addItem("Multisentrik");
		r_indication.addItem("Evrelendirme amaçlı");
		r_indication.addItem("Diğer");
		l_indication.addItem("Evrelendirme amaçlı");
		l_indication.addItem("Diğer");
		MrPoll.updateUi(panel, list);
		onR_nofindingClick(null);
		onL_nofindingClick(null);
		onR_indicationChange(null);
		onL_indicationChange(null);
	}

	@UiHandler("r_nofinding")
	void onR_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !r_nofinding.getValue();
		r_mass.setEnabled(lockStatus);
		r_axillary.setEnabled(lockStatus);
		r_nonmassstain.setEnabled(lockStatus);
		r_ductalstain.setEnabled(lockStatus);
		r_focallesion.setEnabled(lockStatus);
		r_mass.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_axillary.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_nonmassstain.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_ductalstain.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_focallesion.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		onR_massClick(null);
		onR_axillaryClick(null);
	}

	@UiHandler("l_nofinding")
	void onL_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !l_nofinding.getValue();
		l_mass.setEnabled(lockStatus);
		l_axillary.setEnabled(lockStatus);
		l_nonmassstain.setEnabled(lockStatus);
		l_ductalstain.setEnabled(lockStatus);
		l_focallesion.setEnabled(lockStatus);
		l_mass.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_axillary.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_nonmassstain.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_ductalstain.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_focallesion.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		onL_massClick(null);
		onL_axillaryClick(null);
	}

	@UiHandler("r_axillary")
	void onR_axillaryClick(ClickEvent event) {
		Boolean lockStatus = r_axillary.getValue() && r_axillary.isEnabled();
		r_lymphnodecount.setEnabled(lockStatus);
		r_largestnoderadius.setEnabled(lockStatus);
		r_capsuleinvasion.setEnabled(lockStatus);
		r_lymphnodecount.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_lymphnodecount_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_largestnoderadius.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_largestnoderadius_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_capsuleinvasion.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
	}

	@UiHandler("l_axillary")
	void onL_axillaryClick(ClickEvent event) {
		Boolean lockStatus = l_axillary.getValue() && l_axillary.isEnabled();
		l_lymphnodecount.setEnabled(lockStatus);
		l_largestnoderadius.setEnabled(lockStatus);
		l_capsuleinvasion.setEnabled(lockStatus);
		l_lymphnodecount.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_lymphnodecount_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_largestnoderadius.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_largestnoderadius_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_capsuleinvasion.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");

	}

	@UiHandler("r_mass")
	void onR_massClick(ClickEvent event) {
		Boolean lockStatus = r_mass.getValue() && r_mass.isEnabled();
		r_lesionnumber.setEnabled(lockStatus);
		r_lesionspread.setEnabled(lockStatus);
		r_lesionsize.setEnabled(lockStatus);
		r_lesionnumber_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_lesionspread_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		r_lesionsize_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
	}

	@UiHandler("l_mass")
	void onL_massClick(ClickEvent event) {
		Boolean lockStatus = l_mass.getValue() && l_mass.isEnabled();
		l_lesionnumber.setEnabled(lockStatus);
		l_lesionspread.setEnabled(lockStatus);
		l_lesionsize.setEnabled(lockStatus);
		l_lesionnumber_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_lesionspread_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
		l_lesionsize_label.getElement().getStyle()
				.setColor(!lockStatus ? "#A8A8A8" : "black");
	}

	@UiHandler("l_indication")
	void onL_indicationChange(ChangeEvent event) {
		Boolean isLast = l_indication.getSelectedIndex() == 1;
		l_indication_other.setVisible(isLast);
		l_indication_other.setEnabled(isLast);
	}

	@UiHandler("r_indication")
	void onR_indicationChange(ChangeEvent event) {
		Boolean isLast = r_indication.getSelectedIndex() == 1;
		r_indication_other.setVisible(isLast);
		r_indication_other.setEnabled(isLast);
	}

}
