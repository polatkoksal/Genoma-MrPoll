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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UserUpdate extends Composite {

	private static UpdateUserUiBinder uiBinder = GWT
			.create(UpdateUserUiBinder.class);

	UserServiceAsync service = GWT.create(UserService.class);
	
	UserUI newUser = new UserUI();
	
	interface UpdateUserUiBinder extends UiBinder<Widget, UserUpdate> {
	}

	public UserUpdate() {
		initWidget(uiBinder.createAndBindUi(this));
		setUserInfo();
	}

	
	@UiField TextBox username;
	@UiField TextBox name;
	@UiField TextBox surname;
	@UiField TextBox hospital;
	@UiField TextBox phone;
	@UiField TextBox email;
	@UiField Button save;
	@UiField Button cancel;
	@UiField PasswordTextBox currentpassword;
	@UiField PasswordTextBox newpassword;
	@UiField PasswordTextBox newpasswordrepeat;
	@UiField Button savepassword;
	@UiField Button changepassword;
	@UiField Button cancelpassword;
	@UiField Label oldlabel;
	@UiField Label newlabel;
	@UiField Label newlabelrepeat;

	public void setUserInfo(){
		
		setVisibilityPassword(false);
		
		service.getSessionUser("loginUser", new AsyncCallback<UserUI>() {
			
			@Override
			public void onSuccess(UserUI result) {
				username.setText(result.getUsername());
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
		
		newUser.setUsername(username.getText());
		newUser.setName(name.getText());
		newUser.setSurname(surname.getText());
		newUser.setPhone(phone.getText());
		newUser.setEmail(email.getText());
		newUser.setHospital(hospital.getText());
		
		
		service.updateUser("loginUser", newUser, new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("Kullanıcı Bilgileri Yenilendi!");
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
	@UiHandler("changepassword")
	void onChangepasswordClick(ClickEvent event) {
		setVisibilityPassword(true);
		currentpassword.setText("");
		newpassword.setText("");
		newpasswordrepeat.setText("");
	}
	
	@UiHandler("savepassword")
	void onSavepasswordClick(ClickEvent event) {
		
		
		if(newpassword.getText().equals(newpasswordrepeat.getText())){
			
			service.passwordCheck(currentpassword.getText(), "loginUser", new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if(result){
						UserUI user = new UserUI();
						user.setPassword(newpassword.getText());				
						
						service.updateUser("loginUser", user, new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								setVisibilityPassword(false);
								Window.alert("Şifreniz degiştirildi");								
							}
							
							@Override
							public void onFailure(Throwable caught) {							
								Window.alert("change password hata!");
							}
						});
					}
					else{
						Window.alert("Eski şifrenizi yanlış girdiniz!!!");
					}					
				}
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("passwordCheck failure!!!");
				}
			});	
		}
		else{
			Window.alert("Yeni şifreleriniz eşleşmiyor!!!");
		}
					
				
				
	
	}
	@UiHandler("cancelpassword")
	void onCancelpasswordClick(ClickEvent event) {
		setVisibilityPassword(false);
	}
	
	void setVisibilityPassword(Boolean b){
		currentpassword.setVisible(b);
		newpassword.setVisible(b);
		newpasswordrepeat.setVisible(b);
		cancelpassword.setVisible(b);
		savepassword.setVisible(b);
		oldlabel.setVisible(b);
		newlabel.setVisible(b);
		newlabelrepeat.setVisible(b);
	}
	

}
