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
import com.google.gwt.user.client.ui.PasswordTextBox;

public class UserNew extends Composite{

	private static UserInformationUiBinder uiBinder = GWT
			.create(UserInformationUiBinder.class);

	UserServiceAsync newUserService = GWT.create(UserService.class);
	
	interface UserInformationUiBinder extends UiBinder<Widget, UserNew> {
	}

	public UserNew() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@UiField TextBox username;
	@UiField PasswordTextBox password;
	@UiField TextBox name;
	@UiField TextBox surname;
	@UiField TextBox hospital;
	@UiField TextBox phone;
	@UiField TextBox email;
	@UiField Button save;
	@UiField Button cancel;
	@UiField PasswordTextBox passwordrepeat;
	

	
	
	public UserNew(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
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
		
		if(newUser.getPassword().equals(passwordrepeat.getText())){
			newUserService.addUser(newUser, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.alert("Kullanıcı Eklendi!");
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
		else{
			Window.alert("Şifreleriniz Uyuşmuyor");
		}
		
		
		
	}
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
