package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	

	void getAll(AsyncCallback<List<User>> callback);

	void validateUser(String userName, String password, AsyncCallback<Boolean> callback);

	void getSessionUser(String param, AsyncCallback<UserUI> callback);

	void addUser(UserUI userUi, AsyncCallback<Boolean> callback);

	void deleteUser(UserUI userUi, AsyncCallback<Boolean> callback);

	void updateUser(String sessionParam, UserUI userUi, AsyncCallback<Boolean> callback);

	void searchUser(AsyncCallback<List<UserUI>> callback);

	void isAdmin(AsyncCallback<Boolean> callback);

	void putSessionUser(String param, UserUI userUi, AsyncCallback<Void> callback);

	void getSessionString(AsyncCallback<String> callback);

	void sendMail(String receiver, String userName, String password, AsyncCallback<Void> callback);

	void passwordCheck(String password, String sessionParam, AsyncCallback<Boolean> callback);
	
	void resetPassword(String email, AsyncCallback<Boolean> callback);


}
