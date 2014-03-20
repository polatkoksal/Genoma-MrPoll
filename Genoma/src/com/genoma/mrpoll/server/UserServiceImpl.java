package com.genoma.mrpoll.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;

import com.genoma.mrpoll.client.EMF;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.UserRole;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



public class UserServiceImpl extends RemoteServiceServlet implements UserService{

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory factory;
	
	
	@Override
	public List<User> getAll() {
		
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select t from User t");
		List<User> users = query.getResultList();
		return users;
	}
	
	
	@Override
	public Boolean addUser(UserUI userUi) {
		
		Boolean result = true;
		
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select u from User u where u.username=:userName");
		query.setParameter("userName", user.getUsername());
		List<User> users = query.getResultList();
		if(!users.isEmpty()){
			result = false;
		}
		
		if(result){
			factory = EMF.get();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
		}
		return result;
		
	}

	
	public Boolean deleteUser(UserUI userUi){
		
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		User tempUser = em.find(User.class, user.getId());
		
		Query query = em.createQuery("select ur from UserRole ur where ur.user.id = :param");
		query.setParameter("param", user.getId());
		List<UserRole> userRoles = query.getResultList();
		for(UserRole userRole : userRoles){
			em.getTransaction().begin();
			em.remove(userRole);
			em.getTransaction().commit();
		}
		
		em.getTransaction().begin();
		em.remove(tempUser);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	
	
	
	public Boolean updateUser(String sessionParam, UserUI userUi){
		
		Boolean result = true;
		User user = new User();
		HttpSession session = this.getThreadLocalRequest().getSession();
		User sessionUser = (User) session.getAttribute(sessionParam);
		
		try {
			BeanUtils.copyProperties(user, userUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setId(sessionUser.getId());
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		User tempUser = em.find(User.class, user.getId());
		Query query = em.createQuery("select u from User u where u.username=:userName");
		query.setParameter("userName", user.getUsername());
		List<User> users = query.getResultList();
		if(!users.isEmpty()){
			if(tempUser.getId() != users.get(0).getId()){
				result = false;
			}
		}
		
		
		if(result){
			em.getTransaction().begin();
			tempUser.setUsername(user.getUsername());
			tempUser.setPassword(user.getPassword());
			tempUser.setName(user.getName());
			tempUser.setSurname(user.getSurname());
			tempUser.setPhone(user.getPhone());
			tempUser.setEmail(user.getEmail());
			tempUser.setHospital(user.getHospital());
			em.getTransaction().commit();
			em.close();
			session.setAttribute(sessionParam, tempUser);
			
		}	
		
		return result;
	}
	
	
	
	public Boolean validateUser(String userName, String password){
		
		Boolean result = false;
		//String decryptPassword = null;
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select u from User u where u.username=:userName");
		query.setParameter("userName", userName);
		List<User> users=query.getResultList();
		
		if (!users.isEmpty()){
			/*try {
				decryptPassword = decrypt(users.get(0).getPassword(), "a");
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			//if(decryptPassword.equals(password)){
			if(users.get(0).getPassword().equals(password)){
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute("loginUser", users.get(0));
				result = true;
			}
			
		}
		return result;
	}
	
	public UserUI getSessionUser(String param){
		User sessionUser = new User();
		HttpSession session = this.getThreadLocalRequest().getSession();
		sessionUser = (User)session.getAttribute(param);
		
		
		UserUI newUser = new UserUI();
		try {
			BeanUtils.copyProperties(newUser, sessionUser);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return newUser;
	}
	
	
	
	
	public Boolean isAdmin(){
		Boolean result = false;
		HttpSession session = this.getThreadLocalRequest().getSession();
		User user = (User)session.getAttribute("loginUser");
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select ur from UserRole ur where ur.user.id=:param");
		query.setParameter("param", user.getId());
		List<UserRole> userRoles = query.getResultList();
		if(!userRoles.isEmpty() && userRoles.get(0).getRole().getName().equals("admin")){
			result = true; 
		}
		return result;
		
	}
	
	

	public List<UserUI> searchUser(String coulmn,String name){
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute("currentSearch", name);
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select u from User u where u."+coulmn+"=:name");
		query.setParameter("name", name);
		List<User> users=query.getResultList();
		List<UserUI> usersUi = new ArrayList<UserUI>();
		
		for(User user : users){
			try {
				UserUI tempUserUi = new UserUI();
				BeanUtils.copyProperties(tempUserUi, user);
				usersUi.add(tempUserUi);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
						
		}
		return usersUi;
	}
	
	
	
	public void putSessionUser(String param, UserUI userUi){
		
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute(param, user);
		
		
	}
	
	
	public String getSessionString() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		String search = (String) session.getAttribute("currentSearch");
		return search;
	}
	
	
	
	private String encrypt(String text, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		Base64 encoder = new Base64();
		String encrypted = new String(encoder.encode(results));
		//String urlEncrypted = URLEncoder.encode(encrypted, "UTF-8");
		String urlEncrypted = java.net.URLEncoder.encode(encrypted, "UTF-8");
		return urlEncrypted;
	}
	
	private  String decrypt(String text, String key) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		Base64 decoder = new Base64();

		byte[] results = cipher.doFinal((byte[]) decoder.decode(text));

		return new String(results, "UTF-8");
	}


	@Override
	public void sendMail(String receiver) {
		
		
		
	}


	
	
}
