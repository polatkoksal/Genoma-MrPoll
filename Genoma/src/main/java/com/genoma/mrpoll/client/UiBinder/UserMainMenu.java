package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.uihelper.EditVisitData;
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
		back.setVisible(false);
		userInformation.setVisible(false);
		analysis.setVisible(false);
		callpatient.setVisible(false);
		newuser.setVisible(false);
		deleteuser.setVisible(false);
		newpatient.setVisible(false);
		userService.isAdmin(new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				back.setVisible(true);
				userInformation.setVisible(true);
				analysis.setVisible(true);
				callpatient.setVisible(true);
				newpatient.setVisible(true);
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
	@UiField Button newpatient;
	
	

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


	
	@UiHandler("newpatient")
	void onNewPatientClick(ClickEvent event) {
		MrPoll.editPatientPanel = new EditPatient(new EditVisitData());
		MrPoll.repaint(State.TAB_PATIENT_INFO);
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
