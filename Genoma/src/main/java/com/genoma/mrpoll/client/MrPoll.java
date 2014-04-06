package com.genoma.mrpoll.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.genoma.mrpoll.client.UiBinder.NewPatient;
import com.genoma.mrpoll.client.UiBinder.SearchVisit;
import com.genoma.mrpoll.client.UiBinder.UserSearchToEdit;
import com.genoma.mrpoll.client.UiBinder.UserToUpdate;
import com.genoma.mrpoll.client.UiBinder.EditPatient;
import com.genoma.mrpoll.client.UiBinder.UserNew;
import com.genoma.mrpoll.client.UiBinder.UserUpdate;
import com.genoma.mrpoll.client.UiBinder.UserLogin;
import com.genoma.mrpoll.client.UiBinder.UserMainMenu;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.sun.java.swing.plaf.windows.resources.windows;

public class MrPoll implements EntryPoint {
	
	HandlerRegistration confirmation;
	static PatientServiceAsync service= GWT.create(PatientService.class);
	static UserServiceAsync userService = GWT.create(UserService.class);
	
	public static enum State{
		USER_LOGIN, 
		MAIN_MENU, 
		USER_UPDATE, 
		NEW_USER, 
		NEW_PATIENT,
		USER_SEARCH,
		VISIT_SEARCH,
		USER_TO_UPDATE, 
		TAB_PATIENT_INFO,
		TAB_VISIT,
		TAB_CLINIC,
		TAB_MAMMOGRAPHY,
		TAB_USG,
		TAB_MRI,
		TAB_PATHOLOGY,
		TAB_SECOND,
		TAB_SURGICAL
	} 
	
	public void onModuleLoad() {
		addCloseConfirmation();
		repaint(State.USER_LOGIN);	
	}
	
	public static EditPatient editPatientPanel;
	
	public static void repaint(State s){
		
		RootPanel.get().clear();
		switch(s){
			case USER_LOGIN:
				RootPanel.get().add(new UserLogin());
				break;
			case MAIN_MENU:
				RootPanel.get().add(new UserMainMenu());
				break;
			case NEW_USER:
				RootPanel.get().add(new UserNew());
				break;
			case USER_UPDATE:
				RootPanel.get().add(new UserUpdate());
				break;
			case USER_SEARCH: 
				RootPanel.get().add(new UserSearchToEdit(s));
				break;
			case USER_TO_UPDATE:
				RootPanel.get().add(new UserToUpdate());
				break;
			case VISIT_SEARCH:
				RootPanel.get().add(new SearchVisit());
				break;
			case TAB_PATIENT_INFO:
				editPatientPanel.repaint(s);
				RootPanel.get().add(editPatientPanel);
				break;
			default:
				Window.alert("Please See Your Provider!!!");
				break;
		}
		
	}
	
	public static void setEditVisitData(EditVisitData editVisitData){
		
		if(editVisitData == null){
			final EditVisitData edVisitData = new EditVisitData();
			
			userService.getSessionUser("loginUser", new AsyncCallback<UserUI>() {

				@Override
				public void onSuccess(UserUI result) {
					PatientUI patientUI = new PatientUI();
					VisitUI visitUI = new VisitUI();
					List<AnswerUI> answers = new ArrayList<AnswerUI>();
					visitUI.setHospital(result.getHospital());
					edVisitData.setPatient(patientUI);
					edVisitData.setVisit(visitUI);
					edVisitData.setAnswers(answers);
					editPatientPanel = new EditPatient(edVisitData);	
					MrPoll.repaint(State.TAB_PATIENT_INFO);
				}
				
				@Override
				public void onFailure(Throwable arg0) {
					Window.alert("setVisitData Error!");
				}
			});
		}
		else{
			Window.alert("in else!");
			editPatientPanel = new EditPatient(editVisitData);	
			MrPoll.repaint(State.TAB_PATIENT_INFO);
		}
		
	}
	
	
	private static void addCloseConfirmation(){
		Window.addWindowClosingHandler(new ClosingHandler(){

			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("Çıkmak İstediginizden emin misiniz?");
				
			}});
	}
	
	public static void setAnswerOf(HasName w, String answer){
		if(((FocusWidget)w).isEnabled()){
			if(w instanceof TextBox){ 
				((TextBox) w).setText(answer); 
			}
			else if(w instanceof CheckBox){ 
				((CheckBox) w).setValue(Boolean.parseBoolean(answer)); 
			}
			else if(w instanceof ListBox){
				((ListBox) w).setSelectedIndex(Integer.parseInt(answer)); 
			} 
		}
	}
	
	public static AnswerUI returnAnswerOf(HasName w){ 
		AnswerUI atr= new AnswerUI(); 
		String i = "";
		if(w.getName()!=null && w.getName()!=""){
			i=(w.getName());
		}
		atr.setQuestionCode(i); 
		String s=""; 
		if(!((FocusWidget)w).isEnabled()){
			s=null;
		} 
		else if(w instanceof TextBox){ 
			s=((TextBox) w).getText(); 
		}
		else if(w instanceof CheckBox){ 
			s=((CheckBox) w).getValue().toString(); 
		}
		else if(w instanceof ListBox){
			s=((ListBox) w).getSelectedIndex()+""; 
		} 
		atr.setAnswerValue(s); 
		return atr; 
	}

}
