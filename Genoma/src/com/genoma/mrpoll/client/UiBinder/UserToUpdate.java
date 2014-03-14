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
	@UiField Button changepassword;
	@UiField Label oldlabel;
	@UiField Label newlabel;
	@UiField Label newlabelrepeat;
	@UiField PasswordTextBox currentpassword;
	@UiField PasswordTextBox newpassword;
	@UiField PasswordTextBox newpasswordrepeat;
	@UiField Button savepassword;
	@UiField Button cancelpassword;
	

	
	
	/*public UserToUpdate(UserUI userUi) {
		initWidget(uiBinder.createAndBindUi(this));
		this.userUi=userUi;
	}*/

	public void getUserFromSession(){
		setVisibilityPassword(false);
		
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
		
		service.getSessionUser("currentUser", new AsyncCallback<UserUI>() {

			@Override
			public void onSuccess(UserUI result) {
				
				userUi.setPassword(result.getPassword());
				
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
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
	}
	
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.USER_SEARCH_BACK);
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
		
		service.getSessionUser("currentUser", new AsyncCallback<UserUI>() {
			
			@Override
			public void onSuccess(UserUI result) {
				if(currentpassword.getText().equals(result.getPassword())){
					if(newpassword.getText().equals(newpasswordrepeat.getText())){
						
						userUi.setPassword(newpassword.getText());				
						userUi.setUsername(result.getUsername());
						userUi.setName(result.getName());
						userUi.setSurname(result.getSurname());
						userUi.setPhone(result.getPhone());
						userUi.setEmail(result.getEmail());
						userUi.setHospital(result.getHospital());
						
						service.updateUser("currentUser", userUi, new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								setVisibilityPassword(false);
								Window.alert("Şifre degiştirildi");
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
							
								Window.alert("change password hata!");
							}

						});
					}
					else{
						Window.alert("Yeni Şifreleriniz Uymuyor!");
					}
					
				}
				else{
					Window.alert("Eski Şifreniz Yanlış Girildi!");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}

			
		});
		
		
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
