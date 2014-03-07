package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.domain.User;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UserUpdate extends Composite {

	private static UpdateUserUiBinder uiBinder = GWT
			.create(UpdateUserUiBinder.class);

	UserServiceAsync service = GWT.create(UserService.class);
	
	interface UpdateUserUiBinder extends UiBinder<Widget, UserUpdate> {
	}

	public UserUpdate() {
		initWidget(uiBinder.createAndBindUi(this));
		setUserInfo();
	}

	
	@UiField TextBox username;
	@UiField TextBox password;
	@UiField TextBox name;
	@UiField TextBox surname;
	@UiField TextBox phone;
	@UiField TextBox email;
	@UiField Button save;
	@UiField Button cancel;
	@UiField TextBox hospital;

	public UserUpdate(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	public void setUserInfo(){
		service.getSessionUser("sessionUser", new AsyncCallback<UserUI>() {
			
			@Override
			public void onSuccess(UserUI result) {
				username.setText(result.getUsername());
				password.setText(result.getPassword());
				name.setText(result.getName());
				surname.setText(result.getSurname());
				phone.setText(result.getPhone());
				email.setText(result.getEmail());
				hospital.setText(result.getHospital());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("updateUser serivce hatası");
			}

			
		});	
		
	}

	@UiHandler("save")
	void onSaveClick(ClickEvent event) {
		
		UserUI newUser = new UserUI();
		newUser.setUsername(username.getText());
		newUser.setPassword(password.getText());
		newUser.setName(name.getText());
		newUser.setSurname(surname.getText());
		newUser.setPhone(phone.getText());
		newUser.setEmail(email.getText());
		newUser.setHospital(hospital.getText());
		
		service.updateUser("sessionUser", newUser, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("Kullanıcı Bilgileri Yenilendi!");
					MrPoll.repaint(State.MAIN_MENU);
				}
				else{
					Window.alert("Bu Kullanıcı Adı Daha Önce Oluşturuldu!");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Hata!!!");
			}
		});
	}
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
