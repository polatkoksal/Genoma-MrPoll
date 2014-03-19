package com.genoma.mrpoll.client;

import java.util.List;

import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	
	
	public List<User> getAll();
	public Boolean validateUser(String userName, String password);
	public UserUI getSessionUser(String param);
	public Boolean addUser(UserUI userUi);
	public Boolean deleteUser(UserUI userUi);	
	public Boolean updateUser(String sessionParam, UserUI userUi);
	public List<UserUI> searchUser(String coulmn,String name);
	public Boolean isAdmin();
	public void putSessionUser(String param, UserUI userUi);	
	public String getSessionString();
	public void sendMail(String receiver);
	
}
