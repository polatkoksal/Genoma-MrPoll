package com.genoma.mrpoll.client.UiBinder;

import java.io.Serializable;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;


public class Tabs extends Composite implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Updater tab;

	private static TabsUiBinder uiBinder = GWT.create(TabsUiBinder.class);
	
	@UiField VerticalPanel panel;
	@UiField PushButton patientinfo;
	@UiField PushButton visit;
	@UiField PushButton clinic;
	@UiField PushButton mammography;
	@UiField PushButton mri;
	@UiField PushButton second;
	@UiField PushButton usg;
	@UiField PushButton surgical;
	@UiField PushButton pathology;
	

	interface TabsUiBinder extends UiBinder<Widget, Tabs> {
	}

	public Tabs() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public Tabs(State s, Updater tab) {
		this();
		this.tab=tab;

		switch (s){
			case TAB_PATIENT_INFO:
				patientinfo.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
		
			case TAB_VISIT:
				visit.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_CLINIC:
				clinic.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_MAMMOGRAPHY:
				mammography.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_USG:
				usg.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_MRI:
				mri.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_PATHOLOGY:
				pathology.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_SECOND:
				second.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			
			case TAB_SURGICAL:
				surgical.getElement().getStyle().setFontWeight(FontWeight.BOLD);
				break;
			default:
				break;
		}
		for(Widget widget:panel){
			((PushButton) widget).addMouseOverHandler(new MouseOverHandler(){
				Widget target;
				MouseOverHandler init(Widget w){
					target=w;
					return this;
				}
				public void onMouseOver(MouseOverEvent event) {
					target.getElement().getStyle().setBackgroundColor("#A8A8A8");
				}
			}.init(widget));
			
			((PushButton) widget).addMouseOutHandler(new MouseOutHandler(){
				Widget target;
				MouseOutHandler init(Widget w){
					target=w;
					return this;
				}
				public void onMouseOut(MouseOutEvent event) {
					target.getElement().getStyle().setBackgroundColor("white");
				}
			}.init(widget));
		}
	}
	

	@UiHandler("patientinfo")
	void onPatientinfoClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_PATIENT_INFO);
	}
	
	@UiHandler("visit")
	void onVisitClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_VISIT);
	}
	
	@UiHandler("clinic")
	void onClinicClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_CLINIC);
	}
	
	@UiHandler("mammography")
	void onMammographyClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_MAMMOGRAPHY);
	}
	
	@UiHandler("usg")
	void onUsgClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_USG);
	}
	
	@UiHandler("mri")
	void onMriClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_MRI);
	}
	
	@UiHandler("pathology")
	void onPathologyClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_PATHOLOGY);
	}
	
	@UiHandler("second")
	void onSecondClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_SECOND);
	}
	
	@UiHandler("surgical")
	void onSurgicalClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_SURGICAL);
	}
}
