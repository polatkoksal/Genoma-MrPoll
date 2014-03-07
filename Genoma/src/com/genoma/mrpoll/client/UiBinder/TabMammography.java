package com.genoma.mrpoll.client.UiBinder;

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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class TabMammography extends Composite{

	private static MammographyUiBinder uiBinder = GWT
			.create(MammographyUiBinder.class);
	@UiField CheckBox r_nofinding;
	@UiField CheckBox r_mass;
	@UiField TextBox r_lesionnumber;
	@UiField Label r_lesionnumber_label;
	@UiField ListBox r_lesionspread;
	@UiField Label r_lesionspread_label;
	@UiField TextBox r_lesionsize;
	@UiField Label r_lesionsize_label;
	@UiField CheckBox r_capsuleinvasion;
	@UiField CheckBox l_nofinding;
	@UiField CheckBox l_mass;
	@UiField TextBox l_lesionnumber;
	@UiField Label l_lesionnumber_label;
	@UiField ListBox l_lesionspread;
	@UiField Label l_lesionspread_label;
	@UiField TextBox l_lesionsize;
	@UiField Label l_lesionsize_label;
	@UiField CheckBox r_nonmass;
	@UiField CheckBox l_nonmass;
	@UiField CheckBox r_microcalcification;
	@UiField CheckBox l_microcalcification;
	@UiField ListBox r_microcalcificationtype;
	@UiField Label r_microcalcificationtype_label;
	@UiField ListBox r_microcalcificationarea;
	@UiField Label r_microcalcificationarea_label;
	@UiField CheckBox r_axillary;
	@UiField CheckBox l_axillary;
	@UiField TextBox r_lymphnodecount;
	@UiField TextBox r_largestnoderadius;
	@UiField Label r_lymphnodecount_label;
	@UiField Label r_largestnoderadius_label;
	@UiField CheckBox l_capsuleinvasion;
	@UiField ListBox l_microcalcificationtype;
	@UiField ListBox l_microcalcificationarea;
	@UiField Label l_microcalcificationtype_label;
	@UiField Label l_microcalcificationarea_label;
	@UiField TextBox l_lymphnodecount;
	@UiField TextBox l_largestnoderadius;
	@UiField Label l_lypmhnodecount_label;
	@UiField Label l_largestnoderadius_label;
	@UiField ListBox r_nonmass_combo;
	@UiField ListBox l_nonmass_combo;

	interface MammographyUiBinder extends UiBinder<Widget, TabMammography> {
	}

	public TabMammography() {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		onR_nofindingClick(null);
		onL_nofindingClick(null);
	}
	@UiHandler("r_nofinding")
	void onR_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !r_nofinding.getValue();
		r_mass.setEnabled(lockStatus);
		r_axillary.setEnabled(lockStatus);
		r_microcalcification.setEnabled(lockStatus);
		r_nonmass.setEnabled(lockStatus);
		onR_massClick(null);
		onR_nonmassClick(null);
		onR_axillaryClick(null);
		onR_microcalcificationClick(null);
	}
	@UiHandler("l_nofinding")
	void onL_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !l_nofinding.getValue();
		l_mass.setEnabled(lockStatus);
		l_axillary.setEnabled(lockStatus);
		l_microcalcification.setEnabled(lockStatus);
		l_nonmass.setEnabled(lockStatus);
		onL_massClick(null);
		onL_nonmassClick(null);
		onL_axillaryClick(null);
		onL_microcalcificationClick(null);
	}
	
	@UiHandler("r_axillary")
	void onR_axillaryClick(ClickEvent event) {
		Boolean lockStatus = r_axillary.getValue() && r_axillary.isEnabled();
		r_lymphnodecount.setEnabled(lockStatus);
		r_largestnoderadius.setEnabled(lockStatus);
		r_capsuleinvasion.setEnabled(lockStatus);
	}
	@UiHandler("l_axillary")
	void onL_axillaryClick(ClickEvent event) {
		Boolean lockStatus = l_axillary.getValue() && l_axillary.isEnabled();
		l_lymphnodecount.setEnabled(lockStatus);
		l_largestnoderadius.setEnabled(lockStatus);
		l_capsuleinvasion.setEnabled(lockStatus);
	}
	@UiHandler("r_mass")
	void onR_massClick(ClickEvent event) {
		Boolean lockStatus = r_mass.getValue() && r_mass.isEnabled();
		r_lesionnumber.setEnabled(lockStatus);
		r_lesionspread.setEnabled(lockStatus);
		r_lesionsize.setEnabled(lockStatus);
	}
	@UiHandler("l_mass")
	void onL_massClick(ClickEvent event) {
		Boolean lockStatus = l_mass.getValue() && l_mass.isEnabled();
		l_lesionnumber.setEnabled(lockStatus);
		l_lesionspread.setEnabled(lockStatus);
		l_lesionsize.setEnabled(lockStatus);
	}
	@UiHandler("r_microcalcification")
	void onR_microcalcificationClick(ClickEvent event) {
		Boolean lockStatus = r_microcalcification.getValue() && r_microcalcification.isEnabled();
		r_microcalcificationarea.setEnabled(lockStatus);
		r_microcalcificationtype.setEnabled(lockStatus);
	}
	@UiHandler("l_microcalcification")
	void onL_microcalcificationClick(ClickEvent event) {
		Boolean lockStatus = l_microcalcification.getValue() && l_microcalcification.isEnabled();
		l_microcalcificationarea.setEnabled(lockStatus);
		l_microcalcificationtype.setEnabled(lockStatus);
	}
	@UiHandler("l_nonmass")
	void onL_nonmassClick(ClickEvent event) {
		Boolean lockStatus = l_nonmass.getValue() && l_nonmass.isEnabled();
		l_nonmass_combo.setEnabled(lockStatus);
	}
	@UiHandler("r_nonmass")
	void onR_nonmassClick(ClickEvent event) {
		Boolean lockStatus = r_nonmass.getValue() && r_nonmass.isEnabled();
		r_nonmass_combo.setEnabled(lockStatus);
	}
}
