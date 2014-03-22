package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TabMRI extends Composite implements Updater{

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
	@UiField CheckBox r_axillary;
	@UiField CheckBox l_axillary;
	@UiField TextBox r_lymphnodecount;
	@UiField TextBox r_largestnoderadius;
	@UiField Label r_lymphnodecount_label;
	@UiField Label r_largestnoderadius_label;
	@UiField CheckBox l_capsuleinvasion;
	@UiField TextBox l_lymphnodecount;
	@UiField TextBox l_largestnoderadius;
	@UiField Label l_lypmhnodecount_label;
	@UiField Label l_largestnoderadius_label;
	@UiField CheckBox r_nonmassstain;
	@UiField CheckBox r_ductalstain;
	@UiField CheckBox r_focallesion;
	@UiField CheckBox l_nonmassstain;
	@UiField CheckBox l_ductalstain;
	@UiField CheckBox l_focallesion;

	interface MammographyUiBinder extends UiBinder<Widget, TabMRI> {
	}

	public TabMRI(State s) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		r_lesionspread.addItem("Multifokal");
		r_lesionspread.addItem("Multisentrik");
		l_lesionspread.addItem("Multifokal");
		l_lesionspread.addItem("Multisentrik");
		updateUi();
	}
	@UiHandler("r_nofinding")
	void onR_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !r_nofinding.getValue();
		r_mass.setEnabled(lockStatus);
		r_axillary.setEnabled(lockStatus);
		r_nonmassstain.setEnabled(lockStatus);
		r_ductalstain.setEnabled(lockStatus);
		r_focallesion.setEnabled(lockStatus);
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
		onL_massClick(null);
		onL_axillaryClick(null);
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
	public void updateUi(){
		service.getAnswersFromSession(new AsyncCallback<List<Answer>>() {
			
			@Override
			public void onSuccess(List<Answer> result) {
				for(Answer a:result){
					switch (a.getBelongsQuestionId()){
						case 400:	r_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));				break;
						case 410:	r_mass.setValue(Boolean.parseBoolean(a.getAnswer()));							break;
						case 411:	r_lesionnumber.setText(a.getAnswer());														break;
						case 412:	r_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
						case 413:	r_lesionsize.setText(a.getAnswer());															break;
						case 420:	r_nonmassstain.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 421:	r_ductalstain.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 422:	r_focallesion.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 440:	r_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));					break;
						case 441:	r_lymphnodecount.setText(a.getAnswer());													break;
						case 442:	r_largestnoderadius.setText(a.getAnswer());												break;
						case 443:	r_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));	break;
						case 450:	l_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));				break;
						case 460:	l_mass.setValue(Boolean.parseBoolean(a.getAnswer()));							break;
						case 461:	l_lesionnumber.setText(a.getAnswer());														break;
						case 462:	l_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));	break;
						case 463:	l_lesionsize.setText(a.getAnswer());															break;
						case 470:	l_nonmassstain.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 471:	l_ductalstain.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 472:	l_focallesion.setValue(Boolean.parseBoolean(a.getAnswer()));			break;
						case 490:	l_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));					break;
						case 491:	l_lymphnodecount.setText(a.getAnswer());													break;
						case 492:	l_largestnoderadius.setText(a.getAnswer());												break;
						case 493:	l_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));	break;
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
	public List<Answer> getAnswersFromUi() {
		List<Answer> result=new ArrayList<Answer>();
		result.add(returnAnswerOf(400, r_nofinding));
		result.add(returnAnswerOf(410, r_mass));
		result.add(returnAnswerOf(411, r_lesionnumber));
		result.add(returnAnswerOf(412, r_lesionspread));
		result.add(returnAnswerOf(413, r_lesionsize));
		result.add(returnAnswerOf(420, r_nonmassstain));
		result.add(returnAnswerOf(421, r_ductalstain));
		result.add(returnAnswerOf(422, r_focallesion));
		result.add(returnAnswerOf(440, r_axillary));
		result.add(returnAnswerOf(441, r_lymphnodecount));
		result.add(returnAnswerOf(442, r_largestnoderadius));
		result.add(returnAnswerOf(443, r_capsuleinvasion));
		result.add(returnAnswerOf(450, l_nofinding));
		result.add(returnAnswerOf(460, l_mass));
		result.add(returnAnswerOf(461, l_lesionnumber));
		result.add(returnAnswerOf(462, l_lesionspread));
		result.add(returnAnswerOf(463, l_lesionsize));
		result.add(returnAnswerOf(470, l_nonmassstain));
		result.add(returnAnswerOf(471, l_ductalstain));
		result.add(returnAnswerOf(472, l_focallesion));
		result.add(returnAnswerOf(490, l_axillary));
		result.add(returnAnswerOf(491, l_lymphnodecount));
		result.add(returnAnswerOf(492, l_largestnoderadius));
		result.add(returnAnswerOf(493, l_capsuleinvasion));
		return result;
	}
}
