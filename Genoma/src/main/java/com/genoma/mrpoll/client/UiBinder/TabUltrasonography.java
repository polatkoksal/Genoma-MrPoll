package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;

import java.util.ArrayList;
import java.util.List;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
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

public class TabUltrasonography extends Composite implements Updater{

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
	@UiField ListBox l_nonmass_combo;
	@UiField ListBox r_nonmass_combo;

	interface MammographyUiBinder extends UiBinder<Widget, TabUltrasonography> {
	}

	public TabUltrasonography(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		r_lesionspread.addItem("Multifokal");
		r_lesionspread.addItem("Multisentrik");
		l_lesionspread.addItem("Multifokal");
		l_lesionspread.addItem("Multisentrik");
//		updateUi();
	}
	@UiHandler("r_nofinding")
	void onR_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !r_nofinding.getValue();
		r_mass.setEnabled(lockStatus);
		r_axillary.setEnabled(lockStatus);
		r_nonmass.setEnabled(lockStatus);
		onR_massClick(null);
		onR_nonmassClick(null);
		onR_axillaryClick(null);
	}
	@UiHandler("l_nofinding")
	void onL_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !l_nofinding.getValue();
		l_mass.setEnabled(lockStatus);
		l_axillary.setEnabled(lockStatus);
		l_nonmass.setEnabled(lockStatus);
		onL_massClick(null);
		onL_nonmassClick(null);
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
		/*service.getAnswersFromSession(new AsyncCallback<List<Answer>>() {
			
			@Override
			public void onSuccess(List<Answer> result) {
				for(Answer a:result){switch(a.getBelongsQuestionId()){
				case 300:	r_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));									break;
				case 310:	r_mass.setValue(Boolean.parseBoolean(a.getAnswer()));												break;
				case 311:	r_lesionnumber.setText(a.getAnswer());																			break;
				case 312:	r_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));						break;
				case 313:	r_lesionsize.setText(a.getAnswer());																				break;
				case 320:	r_nonmass.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
				case 321:	r_nonmass_combo.setSelectedIndex(Integer.parseInt(a.getAnswer()));					break;
				case 340:	r_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
				case 341:	r_lymphnodecount.setText(a.getAnswer());																		break;
				case 342:	r_largestnoderadius.setText(a.getAnswer());																	break;
				case 343:	r_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));						break;
				case 350:	l_nofinding.setValue(Boolean.parseBoolean(a.getAnswer()));									break;
				case 360:	l_mass.setValue(Boolean.parseBoolean(a.getAnswer()));												break;
				case 361:	l_lesionnumber.setText(a.getAnswer());																			break;
				case 362:	l_lesionspread.setSelectedIndex(Integer.parseInt(a.getAnswer()));						break;
				case 363:	l_lesionsize.setText(a.getAnswer());																				break;
				case 370:	l_nonmass.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
				case 371:	l_nonmass_combo.setSelectedIndex(Integer.parseInt(a.getAnswer()));					break;
				case 390:	l_axillary.setValue(Boolean.parseBoolean(a.getAnswer()));										break;
				case 391:	l_lymphnodecount.setText(a.getAnswer());																		break;
				case 392:	l_largestnoderadius.setText(a.getAnswer());																	break;
				case 393:	l_capsuleinvasion.setValue(Boolean.parseBoolean(a.getAnswer()));						break;
				}
					
				}
				onR_nofindingClick(null);
				onL_nofindingClick(null);
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});*/
	}
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result=new ArrayList<AnswerUI>();
		result.add(returnAnswerOf(300, r_nofinding));
		result.add(returnAnswerOf(310, r_mass));
		result.add(returnAnswerOf(311, r_lesionnumber));
		result.add(returnAnswerOf(312, r_lesionspread));
		result.add(returnAnswerOf(313, r_lesionsize));
		result.add(returnAnswerOf(320, r_nonmass));
		result.add(returnAnswerOf(321, r_nonmass_combo));
		result.add(returnAnswerOf(340, r_axillary));
		result.add(returnAnswerOf(341, r_lymphnodecount));
		result.add(returnAnswerOf(342, r_largestnoderadius));
		result.add(returnAnswerOf(343, r_capsuleinvasion));
		result.add(returnAnswerOf(350, l_nofinding));
		result.add(returnAnswerOf(360, l_mass));
		result.add(returnAnswerOf(361, l_lesionnumber));
		result.add(returnAnswerOf(362, l_lesionspread));
		result.add(returnAnswerOf(363, l_lesionsize));
		result.add(returnAnswerOf(370, l_nonmass));
		result.add(returnAnswerOf(371, l_nonmass_combo));
		result.add(returnAnswerOf(390, l_axillary));
		result.add(returnAnswerOf(391, l_lymphnodecount));
		result.add(returnAnswerOf(392, l_largestnoderadius));
		result.add(returnAnswerOf(393, l_capsuleinvasion));
		return result;
	}
}
