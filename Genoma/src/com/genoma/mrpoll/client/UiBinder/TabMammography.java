package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class TabMammography extends Composite implements Updater{

	PatientServiceAsync service=GWT.create(PatientService.class);
	
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
	@UiField AbsolutePanel absultePanel;

	interface MammographyUiBinder extends UiBinder<Widget, TabMammography> {
	}

	public TabMammography(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		r_lesionspread.addItem("Multifokal");
		r_lesionspread.addItem("Multisentrik");
		l_lesionspread.addItem("Multifokal");
		l_lesionspread.addItem("Multisentrik");
		l_lesionspread.getName();
		absultePanel.getWidget(0).getElement(); // Widget in tipine göre cast at ve hangi tipte ise ona göre value sunu al.
//		updateUi();
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
	
	public void updateUi(){
		service.getAnswersFromSession(new AsyncCallback<List<Answer>>() {
			
			@Override
			public void onSuccess(List<Answer> result) {
				for(Answer a:result){
					switch(a.getBelongsQuestionId()){
					case 200:	r_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));									break;
					case 210:	r_mass.setValue(Boolean.parseBoolean(a.getAnswer()));												break;
					case 211:	r_lesionnumber.setText(a.getAnswer());																			break;
					case 212:	r_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));						break;
					case 213:	r_lesionsize.setText(a.getAnswer());																				break;
					case 220:	r_nonmass.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
					case 221:	r_nonmass_combo.setSelectedIndex(Integer.parseInt(a.getAnswer()));					break;
					case 230:	r_microcalcification.setValue(Boolean.parseBoolean(a.getAnswer()));					break;
					case 231:	r_microcalcificationtype.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
					case 232:	r_microcalcificationarea.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
					case 240:	r_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
					case 241:	r_lymphnodecount.setText(a.getAnswer());																		break;
					case 242:	r_largestnoderadius.setText(a.getAnswer());																	break;
					case 243:	r_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));						break;
					case 250:	l_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));									break;
					case 260:	l_mass.setValue(Boolean.parseBoolean(a.getAnswer()));												break;
					case 261:	l_lesionnumber.setText(a.getAnswer());																			break;
					case 262:	l_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));						break;
					case 263:	l_lesionsize.setText(a.getAnswer());																				break;
					case 270:	l_nonmass.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
					case 271:	l_nonmass_combo.setSelectedIndex(Integer.parseInt(a.getAnswer()));					break;
					case 280:	l_microcalcification.setValue(Boolean.parseBoolean(a.getAnswer()));					break;
					case 281:	l_microcalcificationtype.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
					case 282:	l_microcalcificationarea.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
					case 290:	l_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
					case 291:	l_lymphnodecount.setText(a.getAnswer());																		break;
					case 292:	l_largestnoderadius.setText(a.getAnswer());																	break;
					case 293:	l_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));						break;
					}
				}
				onR_nofindingClick(null);
				onL_nofindingClick(null);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result=new ArrayList<AnswerUI>();
		result.add(returnAnswerOf(200, r_nofinding));
		result.add(returnAnswerOf(210, r_mass));
		result.add(returnAnswerOf(211, r_lesionnumber));
		result.add(returnAnswerOf(212, r_lesionspread));
		result.add(returnAnswerOf(213, r_lesionsize));
		result.add(returnAnswerOf(220, r_nonmass));
		result.add(returnAnswerOf(221, r_nonmass_combo));
		result.add(returnAnswerOf(230, r_microcalcification));
		result.add(returnAnswerOf(231, r_microcalcificationtype));
		result.add(returnAnswerOf(232, r_microcalcificationarea));
		result.add(returnAnswerOf(240, r_axillary));
		result.add(returnAnswerOf(241, r_lymphnodecount));
		result.add(returnAnswerOf(242, r_largestnoderadius));
		result.add(returnAnswerOf(243, r_capsuleinvasion));
		result.add(returnAnswerOf(250, l_nofinding));
		result.add(returnAnswerOf(260, l_mass));
		result.add(returnAnswerOf(261, l_lesionnumber));
		result.add(returnAnswerOf(262, l_lesionspread));
		result.add(returnAnswerOf(263, l_lesionsize));
		result.add(returnAnswerOf(270, l_nonmass));
		result.add(returnAnswerOf(271, l_nonmass_combo));
		result.add(returnAnswerOf(280, l_microcalcification));
		result.add(returnAnswerOf(281, l_microcalcificationtype));
		result.add(returnAnswerOf(282, l_microcalcificationarea));
		result.add(returnAnswerOf(290, l_axillary));
		result.add(returnAnswerOf(291, l_lymphnodecount));
		result.add(returnAnswerOf(292, l_largestnoderadius));
		result.add(returnAnswerOf(293, l_capsuleinvasion));
		return result;
	}
}
