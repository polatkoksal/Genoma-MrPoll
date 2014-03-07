package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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

public class NewPatient extends Composite {

	private static GeneralInfoUiBinder uiBinder = GWT.create(GeneralInfoUiBinder.class);

	interface GeneralInfoUiBinder extends UiBinder<Widget, NewPatient> {
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
					MrPoll.repaint(target);
				}
			}.init(target));
		}
	}

	public NewPatient() {
		initWidget(uiBinder.createAndBindUi(this));
		panel.add(new Tabs());
	}
	public NewPatient(State s){
		this();
		switch(s){
			case TAB_GENERAL_INFO:
				panel.add(new TabGeneralInfo());
				pointTo(null, back);
				pointTo(State.TAB_CLINIC,forward);
				break;
			case TAB_CLINIC:
				panel.add(new TabClinic());
				pointTo(State.TAB_GENERAL_INFO,back);
				pointTo(State.TAB_MAMMOGRAPHY,forward);
				break;
			case TAB_MAMMOGRAPHY:
				panel.add(new TabMammography());
				pointTo(State.TAB_CLINIC,back);
				pointTo(State.TAB_USG,forward);
				break;
			case TAB_USG:
				panel.add(new TabUltrasonography());
				pointTo(State.TAB_MAMMOGRAPHY, back);
				pointTo(State.TAB_MRI, forward);
				break;
			case TAB_MRI:
				panel.add(new TabMRI());
				pointTo(State.TAB_USG,back);
				pointTo(State.TAB_PATHOLOGY,forward);
				break;
			case TAB_PATHOLOGY:
				panel.add(new TabPathology());
				pointTo(State.TAB_MRI,back);
				pointTo(State.TAB_SECOND,forward);
				break;
			case TAB_SECOND:
				panel.add(new TabSecondVisit());
				pointTo(State.TAB_PATHOLOGY,back);
				pointTo(State.TAB_SURGICAL,forward);
				break;
			case TAB_SURGICAL:
				panel.add(new TabSurgical());
				pointTo(State.TAB_SECOND, back);
				pointTo(null,forward);
				break;
			default:
				break;
		}
	}
	
	@UiField Button mainmenu;
	@UiField Button back;
	@UiField Button forward;
	@UiField Button save;
	@UiField Button delete;
	@UiField Label patientid;
	@UiField HorizontalPanel panel;

	
	@UiHandler("mainmenu")
	void onMainmenuClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
