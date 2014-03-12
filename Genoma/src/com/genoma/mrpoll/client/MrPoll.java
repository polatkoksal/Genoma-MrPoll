package com.genoma.mrpoll.client;

import com.genoma.mrpoll.client.UiBinder.NewPatient;
import com.genoma.mrpoll.client.UiBinder.UserSearchToEdit;
import com.genoma.mrpoll.client.UiBinder.UserToUpdate;
import com.genoma.mrpoll.client.UiBinder.EditPatient;
import com.genoma.mrpoll.client.UiBinder.UserNew;
import com.genoma.mrpoll.client.UiBinder.UserUpdate;
import com.genoma.mrpoll.client.UiBinder.UserLogin;
import com.genoma.mrpoll.client.UiBinder.UserMainMenu;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MrPoll implements EntryPoint {
	
	
	public static enum State{
		USER_LOGIN, 
		MAIN_MENU, 
		USER_UPDATE, 
		NEW_USER, 
		NEW_PATIENT,
		USER_SEARCH, 
		USER_TO_UPDATE, 
		TAB_GENERAL_INFO, 
		TAB_CLINIC,
		TAB_MAMMOGRAPHY,
		TAB_USG,
		TAB_MRI,
		TAB_PATHOLOGY,
		TAB_SECOND,
		TAB_SURGICAL
	} 
	
	public void onModuleLoad() {
		repaint(State.USER_LOGIN);
		
	}
	
	
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
				RootPanel.get().add(new UserSearchToEdit());
				break;
			case USER_TO_UPDATE:
				RootPanel.get().add(new UserToUpdate());
				break;
			case NEW_PATIENT:
				RootPanel.get().add(new NewPatient());
				break;
			default:
				RootPanel.get().add(new EditPatient(s));
				break;
		}
		
		
		
		
		
		//final UserServiceAsync service = GWT.create(UserService.class);
		
		/*button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				
				User user = new User();
				user.setName("Polat");
		
				service.addUser(user, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void arg0) {
						System.out.println("User added!");
					}
					
					@Override
					public void onFailure(Throwable arg0) {
						System.out.println("Error Occured!");
						
					}
				});
			}
		});*/
		
		
		
			
			
	}

	
	

	
}
