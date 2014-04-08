package com.genoma.mrpoll.client.UiBinder;

import static com.genoma.mrpoll.client.MrPoll.returnAnswerOf;
import static com.genoma.mrpoll.client.MrPoll.setAnswerOf;

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
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class TabUltrasonography extends Composite implements Updater{

	PatientServiceAsync service=GWT.create(PatientService.class);

	private static UltrasonographyUiBinder uiBinder = GWT
			.create(UltrasonographyUiBinder.class);
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
	@UiField Label r_lymphnodecount_label;
	@UiField Label r_largestnoderadius_label;
	@UiField CheckBox l_capsuleinvasion;
	@UiField TextBox l_lymphnodecount;
	@UiField TextBox l_largestnoderadius;
	@UiField Label l_lymphnodecount_label;
	@UiField Label l_largestnoderadius_label;
	@UiField AbsolutePanel panel;
	@UiField TextBox r_largestnoderadius;

	interface UltrasonographyUiBinder extends UiBinder<Widget, TabUltrasonography> {
	}

	public TabUltrasonography(List<AnswerUI> list) {
		initWidget(uiBinder.createAndBindUi(this));
		r_nofinding.setValue(true);
		l_nofinding.setValue(true);
		r_lesionspread.addItem("Multifokal");
		r_lesionspread.addItem("Multisentrik");
		l_lesionspread.addItem("Multifokal");
		l_lesionspread.addItem("Multisentrik");
		updateUi(list);
		onL_nofindingClick(null);
		onR_nofindingClick(null);
	}
	void clickey(){
		onL_nofindingClick(null);
		onR_nofindingClick(null);
	}
	@UiHandler("r_nofinding")
	void onR_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !r_nofinding.getValue();
		r_mass.setEnabled(lockStatus);
		r_axillary.setEnabled(lockStatus);
		r_nonmass.setEnabled(lockStatus);
		r_mass.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_axillary.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_nonmass.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		onR_massClick(null);
		onR_axillaryClick(null);
	}
	@UiHandler("l_nofinding")
	void onL_nofindingClick(ClickEvent event) {
		Boolean lockStatus = !l_nofinding.getValue();
		l_mass.setEnabled(lockStatus);
		l_axillary.setEnabled(lockStatus);
		l_nonmass.setEnabled(lockStatus);
		l_mass.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_axillary.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_nonmass.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		onL_massClick(null);
		onL_axillaryClick(null);
	}
	
	@UiHandler("r_axillary")
	void onR_axillaryClick(ClickEvent event) {
		Boolean lockStatus = r_axillary.getValue() && r_axillary.isEnabled();
		r_lymphnodecount.setEnabled(lockStatus);
		r_largestnoderadius.setEnabled(lockStatus);
		r_capsuleinvasion.setEnabled(lockStatus);
		r_lymphnodecount.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_lymphnodecount_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_largestnoderadius.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_largestnoderadius_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_capsuleinvasion.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
	}
	@UiHandler("l_axillary")
	void onL_axillaryClick(ClickEvent event) {
		Boolean lockStatus = l_axillary.getValue() && l_axillary.isEnabled();
		l_lymphnodecount.setEnabled(lockStatus);
		l_largestnoderadius.setEnabled(lockStatus);
		l_capsuleinvasion.setEnabled(lockStatus);
		l_lymphnodecount.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_lymphnodecount_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_largestnoderadius.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_largestnoderadius_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_capsuleinvasion.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
	
	}
	@UiHandler("r_mass")
	void onR_massClick(ClickEvent event) {
		Boolean lockStatus = r_mass.getValue() && r_mass.isEnabled();
		r_lesionnumber.setEnabled(lockStatus);
		r_lesionspread.setEnabled(lockStatus);
		r_lesionsize.setEnabled(lockStatus);
		r_lesionnumber_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_lesionspread_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		r_lesionsize_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
	}
	@UiHandler("l_mass")
	void onL_massClick(ClickEvent event) {
		Boolean lockStatus = l_mass.getValue() && l_mass.isEnabled();
		l_lesionnumber.setEnabled(lockStatus);
		l_lesionspread.setEnabled(lockStatus);
		l_lesionsize.setEnabled(lockStatus);
		l_lesionnumber_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_lesionspread_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
		l_lesionsize_label.getElement().getStyle().setColor(!lockStatus?"#A8A8A8":"black");
	}
	public void updateUi(List<AnswerUI> answers){
		for(AnswerUI answer : answers){
			for(Widget w: panel){
				if(answer.getAnswerValue()!=null&&w instanceof HasName && ((HasName) w).getName().equals(answer.getQuestionCode())){
					setAnswerOf((HasName)w, answer.getAnswerValue());
				}
			}
		}
	}
	@Override
	public List<AnswerUI> getAnswersFromUi() {
		List<AnswerUI> result=new ArrayList<AnswerUI>();
		String s="";
		for(Widget w: panel){
			if(w instanceof HasName&&((FocusWidget)w).isEnabled()){
				result.add(returnAnswerOf((HasName)w));
				s+=((HasName) w).getName()+"-"+returnAnswerOf((HasName)w);
			}
		}
		Window.alert(s);
		return result;
	}
}
