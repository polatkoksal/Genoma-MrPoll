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

public class UserLogin extends Composite {
	
	UserServiceAsync service = GWT.create(UserService.class);
	
	private static UserEnterUiBinder uiBinder = GWT.create(UserEnterUiBinder.class);

	interface UserEnterUiBinder extends UiBinder<Widget, UserLogin> {
	}

	public UserLogin() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Button button;
	@UiField TextBox username;
	@UiField TextBox password;
	
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
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) 
	{	
		service.validateUser(username.getText(), password.getText(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
			
				if(result){	
					MrPoll.repaint(State.MAIN_MENU);
				}
				else{
					Window.alert("Kullanıcı adı veya şifre hatalı!");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("RPC denemesi başarısız oldu.");
			}
		});

	}
		
		/*service.getUser(username.getText(), new AsyncCallback<User>() {
			public void onSuccess() {
				MrPoll.validateUser(username.getText(), password.getText());
				
				
				/*if( result != null && result.getPassword() == password.getText() ){	
					MrPoll.repaint(State.MAIN_MENU);
				}
				else{
					Window.alert("Kullanıcı adı veya şifre hatalı!");
				}
			}
			public void onFailure(Throwable caught) {
				Window.alert("Uzaktan prosedürel çağrı denemesi başarısız oldu.");
			}
		});*/
	

	
	@UiHandler("password")
	void onPasswordKeyDown(KeyDownEvent event) {
		if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER){
			onClick(null);
		}
	}
}
