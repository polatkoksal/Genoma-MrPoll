package com.genoma.mrpoll.client.UiBinder;

import sun.net.www.protocol.mailto.MailToURLConnection;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class UserToUpdate extends Composite{

	private static UserInformationUiBinder uiBinder = GWT
			.create(UserInformationUiBinder.class);
	
	
	UserServiceAsync service = GWT.create(UserService.class);
	UserUI userUi = new UserUI();
	
	interface UserInformationUiBinder extends UiBinder<Widget, UserToUpdate> {
	}

	public UserToUpdate() {
		initWidget(uiBinder.createAndBindUi(this));
		getUserFromSession();
	}

	
	@UiField TextBox username;
	@UiField TextBox name;
	@UiField TextBox surname;
	@UiField TextBox hospital;
	@UiField TextBox phone;
	@UiField TextBox email;
	@UiField Button save;
	@UiField Button cancel;
	
	

	
	
	/*public UserToUpdate(UserUI userUi) {
		initWidget(uiBinder.createAndBindUi(this));
		this.userUi=userUi;
	}*/

	public void getUserFromSession(){
		
		service.getSessionUser("currentUser", new AsyncCallback<UserUI>() {
			
			public void onSuccess(UserUI result) {
				userUi=result;
				username.setText(result.getUsername());
				name.setText(result.getName());
				surname.setText(result.getSurname());
				phone.setText(result.getPhone());
				email.setText(result.getEmail());
				hospital.setText(result.getHospital());
				
			}
			
			public void onFailure(Throwable caught) {
				Window.alert("EditUserResult fail!");
			}

			
		});
	}
	
	@UiHandler("save")
	void onSaveClick(ClickEvent event) {
		
		userUi.setUsername(username.getText());
		userUi.setName(name.getText());
		userUi.setSurname(surname.getText());
		userUi.setPhone(phone.getText());
		userUi.setEmail(email.getText());
		userUi.setHospital(hospital.getText());
		
				
		service.updateUser("currentUser", userUi, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("Kullanıcı Güncellendi!");
					MrPoll.repaint(State.USER_SEARCH_BACK);
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
		MrPoll.repaint(State.USER_SEARCH_BACK);
	}
	
	@UiHandler("resetpassword")
	void onChangepasswordClick(ClickEvent event) {
		
		Boolean b = Window.confirm("Kullanıcı Şifresini Sıfırlamak İstediğinizden Emin misiniz???");
		if(b){
			UserUI user = new UserUI();
			user.setPassword("a");
			
			service.updateUser("currentUser", user, new AsyncCallback<Boolean>() {
	
				@Override
				public void onSuccess(Boolean result) {
					if(result){ 
						Window.alert("Kullanıcı Şifresi Sıfırlandı! ");
						MrPoll.repaint(State.MAIN_MENU);
					}
					else{
						Window.alert("şifre sıfırlanamadı!!!");
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("change password hata!");
				}
	
			});
		}
	}
}
