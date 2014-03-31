package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class UserToDisplay extends Composite {

	UserServiceAsync service = GWT.create(UserService.class);
	
	private static UserSearchResultUiBinder uiBinder = GWT
			.create(UserSearchResultUiBinder.class);
	
	@UiField Label username;
	@UiField Label name;
	@UiField Label surname;
	@UiField Label hospital;
	@UiField Label phone;
	@UiField Label email;
	@UiField Button edit;
	@UiField Button delete;
	
	UserUI userUi;

	interface UserSearchResultUiBinder extends
			UiBinder<Widget, UserToDisplay> {
	}

	public UserToDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public UserToDisplay(UserUI userUi){
		this();
		this.userUi=userUi;
		username.setText(userUi.getUsername());
		name.setText(userUi.getName());
		surname.setText(userUi.getSurname());
		phone.setText(userUi.getPhone());
		email.setText(userUi.getEmail());
		hospital.setText(userUi.getHospital());
	}
	
	@UiHandler("edit")
	void onEditClick(ClickEvent event) {
		service.putSessionUser("currentUser", userUi , new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				MrPoll.repaint(State.USER_TO_UPDATE);
			}
			public void onFailure(Throwable caught) {	
				Window.alert("putsession hatası");
			}
		});
		
		
	}
	@UiHandler("delete")
	void onDeleteClick(ClickEvent event) {
		Boolean b = Window.confirm("Bu kullanıcıyı silmek istediginizden emin misiniz?");
		if(b){
			service.deleteUser(userUi, new AsyncCallback<Boolean>() {
	
				
				@Override
				public void onSuccess(Boolean result) {
					Window.alert("Kullanıcı Silindi");
					MrPoll.repaint(State.USER_SEARCH_BACK);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("delete fail");
				
					
				}
	
			});
		}
	}
}
