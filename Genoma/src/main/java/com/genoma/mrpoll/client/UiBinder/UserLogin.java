package com.genoma.mrpoll.client.UiBinder;

import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.MrPoll.State;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Label;

public class UserLogin extends Composite {
	
	UserServiceAsync service = GWT.create(UserService.class);
	
	private static UserEnterUiBinder uiBinder = GWT.create(UserEnterUiBinder.class);

	interface UserEnterUiBinder extends UiBinder<Widget, UserLogin> {
	}

	public UserLogin() {
		initWidget(uiBinder.createAndBindUi(this));
		setVisibility(false);
	}

	@UiField Button login;
	@UiField TextBox username;
	@UiField TextBox password;
	@UiField Button forgetpassword;
	@UiField Label emaillabel;
	@UiField TextBox email;
	@UiField Button sendpassword;
	@UiField Button cancel;
	
	public TextBox getUsername() {
		return username;
	}

	public void setUsername(TextBox username) {
		this.username = username;
	}

	public TextBox getPassword() {
		return password;
	}

	public void setPassword(TextBox password) {
		this.password = password;
	}

	

	public UserLogin(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		login.setText(firstName);
	}

	@UiHandler("login")
	void onClick(ClickEvent e) 
	{	
		setButtonEnable(false);
		
		service.validateUser(username.getText(), password.getText(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
			
				if(result){	
					MrPoll.repaint(State.MAIN_MENU);
				}
				else{
					Window.alert("Kullanıcı adı veya şifre hatalı!");
					setButtonEnable(true);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("RPC denemesi başarısız oldu.");
				setButtonEnable(true);
			}
		});

	}
		
	
	@UiHandler("password")
	void onPasswordKeyDown(KeyDownEvent event) {
		if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER){
			onClick(null);
		}
	}
	
	@UiHandler("sendpassword")
	void onSendpasswordClick(ClickEvent event) {
		
		setButtonEnable(false);
		service.resetPassword(email.getText(), new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("şifreniz gönderildi");
					setVisibility(false);
					setButtonEnable(true);
				}
				else{
					Window.alert("eşleşen email adresi bulunamadı");
					setButtonEnable(true);
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("send mail fail in userLogin");
				setButtonEnable(true);
			}

		
		});
	}
	
	
	@UiHandler("forgetpassword")
	void onForgetpasswordClick(ClickEvent event) {
		setVisibility(true);	
	}
	
	
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		setVisibility(false);
	}
	
	public void setVisibility(Boolean b){
		emaillabel.setVisible(b);
		email.setVisible(b);
		sendpassword.setVisible(b);
		cancel.setVisible(b);
	}
	
	public void setButtonEnable(Boolean b){
		login.setEnabled(b);
		forgetpassword.setEnabled(b);
		sendpassword.setEnabled(b);
		cancel.setEnabled(b);
	}
}
