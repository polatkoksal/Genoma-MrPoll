package com.genoma.mrpoll.client;

import com.genoma.mrpoll.client.UiBinder.NewPatient;
import com.genoma.mrpoll.client.UiBinder.UserSearchToEdit;
import com.genoma.mrpoll.client.UiBinder.UserToUpdate;
import com.genoma.mrpoll.client.UiBinder.EditPatient;
import com.genoma.mrpoll.client.UiBinder.UserNew;
import com.genoma.mrpoll.client.UiBinder.UserUpdate;
import com.genoma.mrpoll.client.UiBinder.UserLogin;
import com.genoma.mrpoll.client.UiBinder.UserMainMenu;
import com.genoma.mrpoll.domain.Answer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MrPoll implements EntryPoint {
	
	
	public static enum State{
		USER_LOGIN, 
		MAIN_MENU, 
		USER_UPDATE, 
		NEW_USER, 
		NEW_PATIENT,
		USER_SEARCH,
		USER_SEARCH_BACK,
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
	
	public static EditPatient editPatientPanel=null;
	
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
			case USER_SEARCH_BACK:
				RootPanel.get().add(new UserSearchToEdit(s));
				break;
			case USER_TO_UPDATE:
				RootPanel.get().add(new UserToUpdate());
				break;
			case NEW_PATIENT:
				RootPanel.get().add(new NewPatient());
				break;
			default:
				//RootPanel.get().add(new EditPatient(s));
				if(editPatientPanel == null) {
					editPatientPanel = new EditPatient(s);
				}
				editPatientPanel.repaint(s);
				RootPanel.get().add(editPatientPanel);
				break;
		}
		
	}
	
	private static void addCloseConfirmation(){
		Window.addWindowClosingHandler(new ClosingHandler(){

			@Override
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("really?");
				
			}});
	}
	
	public static Answer returnAnswerOf(int i, FocusWidget w){ 
		Answer atr= new Answer(); 
		atr.setBelongsQuestionId(i); 
		String s=""; 
		if(!w.isEnabled()){
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
		atr.setAnswer(s); 
		return atr; 
	}

}
