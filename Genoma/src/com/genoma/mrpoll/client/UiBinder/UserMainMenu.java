package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserMainMenu extends Composite {

	private static UserMainMenuUiBinder uiBinder = GWT
			.create(UserMainMenuUiBinder.class);
	
	UserServiceAsync userService = GWT.create(UserService.class);
	
	interface UserMainMenuUiBinder extends UiBinder<Widget, UserMainMenu> {
	}


	
	public UserMainMenu() {
		initWidget(uiBinder.createAndBindUi(this));
		userService.isAdmin(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				newuser.setVisible(result);
				deleteuser.setVisible(result);
				
			} 
			
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("RPC fails");
				
			}
		
		});
		
		
	}

	@UiField Button back;
	@UiField Button userInformation;
	@UiField Button newuser;
	@UiField Button deleteuser;
	@UiField Button analysis;
	@UiField Button callpatient;
	
	

	public UserMainMenu(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		back.setText(firstName);
		System.out.println("UserMainMenu(String firstName)");
		userInformation.setText(firstName);
		
	}

	@UiHandler("back")
	void onClick(ClickEvent e) {
		Boolean result = Window.confirm("Çıkmak isteginizden emin misiniz?");
		if(result){
			MrPoll.repaint(State.USER_LOGIN);
		}
		
	}


	
	@UiHandler("newPatient")
	void onNewPatientClick(ClickEvent event) {
		MrPoll.repaint(State.TAB_GENERAL_INFO);
	}
	
	
	@UiHandler("userInformation")
	void onUserInformationClick(ClickEvent event) {
		MrPoll.repaint(State.USER_UPDATE);
	}
	@UiHandler("newuser")
	void onNewuserClick(ClickEvent event) {
		MrPoll.repaint(State.NEW_USER);
	}
	@UiHandler("deleteuser")
	void onDeleteuserClick(ClickEvent event) {
		MrPoll.repaint(State.USER_SEARCH);
	}
}
