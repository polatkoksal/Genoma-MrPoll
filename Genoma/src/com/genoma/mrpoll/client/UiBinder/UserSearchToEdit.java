package com.genoma.mrpoll.client.UiBinder;

import java.util.List;
import com.genoma.mrpoll.client.MrPoll;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.client.UserServiceAsync;
import com.genoma.mrpoll.client.MrPoll.State;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class UserSearchToEdit extends Composite {
	
	UserServiceAsync service = GWT.create(UserService.class);
	
	private static SearchUserToEditUiBinder uiBinder = GWT
			.create(SearchUserToEditUiBinder.class);
	
	@UiField Button search;
	@UiField VerticalPanel resultpanel;
	@UiField TextBox searchbox;
	@UiField Button cancel;

	interface SearchUserToEditUiBinder extends UiBinder<Widget, UserSearchToEdit> {
	}

	public UserSearchToEdit() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public UserSearchToEdit(User user){
		
	}
	
	@UiHandler("search")
	void onSearchClick(ClickEvent event) {
		
		service.searchUser("name", searchbox.getText(), new AsyncCallback<List<UserUI>>() {
			
			@Override
			public void onSuccess(List<UserUI> result) {
				resultpanel.clear();
				resultpanel.setHeight((result.size())*(new UserToDisplay().getOffsetHeight())+"");
				for (UserUI userUi : result){
					UserToDisplay searchResult = new UserToDisplay(userUi);
					resultpanel.add(searchResult);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("EditUser servis hatası");
				
			}
		});
		
		
	}
	
	public User convert(UserUI user){
		User result = new User();
		result.setId(user.getId());
		result.setName(user.getName());
		result.setPassword(user.getPassword());
		result.setUsername(user.getUsername());
		result.setSurname(user.getSurname());
		result.setPhone(user.getPhone());
		result.setEmail(user.getEmail());
		return result;
	}

	@UiHandler("searchbox")
	void onSearchboxKeyDown(KeyDownEvent event) {
		if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER){
			onSearchClick(null);
		}
	}
	@UiHandler("cancel")
	void onCancelClick(ClickEvent event) {
		MrPoll.repaint(State.MAIN_MENU);
	}
}
