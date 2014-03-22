package com.genoma.mrpoll.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;

import com.genoma.mrpoll.client.EMF;
import com.genoma.mrpoll.client.UserService;
import com.genoma.mrpoll.domain.Settings;
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
		
		try {
			user.setPassword(encrypt(user.getPassword(), "aaaaaaaaaaaaaaaa"));
		} catch (Exception e) {
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
		try {
			user.setPassword(encrypt(user.getPassword(), "aaaaaaaaaaaaaaaa"));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		String decryptPassword = null;
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select u from User u where u.username=:userName");
		query.setParameter("userName", userName);
		List<User> users=query.getResultList();
		
		if (!users.isEmpty()){
			if(users.get(0).getPassword().equals(password)){
				HttpSession session = this.getThreadLocalRequest().getSession();
				session.setAttribute("loginUser", users.get(0));
				result = true;
			}
		
//			try {
//				decryptPassword = decrypt(users.get(0).getPassword(), "aaaaaaaaaaaaaaaa");
//				if(decryptPassword.equals(password)){
//					HttpSession session = this.getThreadLocalRequest().getSession();
//					session.setAttribute("loginUser", users.get(0));
//					result = true;
//				}
//			} 
//			catch (Exception e) {
//				e.printStackTrace();
//			}
			
			
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
	
	
	
	public String encrypt(String text, String key) throws Exception {
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
		String urlEncrypted = java.net.URLEncoder.encode(encrypted, "UTF-8");
		return urlEncrypted;
	}
	
	public String decrypt(String text, String key) throws Exception {

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


	/*public String encrypt(String text, String secretKey) {
        byte[] raw;
        String encryptedString;
        SecretKeySpec skeySpec;
        byte[] encryptText = text.getBytes();
        Cipher cipher;
        try {
            raw = Base64.decodeBase64(secretKey);
            skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }

    public String decrypt(String text, String secretKey) {
        Cipher cipher;
        String encryptedString;
        byte[] encryptText = null;
        byte[] raw;
        SecretKeySpec skeySpec;
        try {
            raw = Base64.decodeBase64(secretKey);
            skeySpec = new SecretKeySpec(raw, "AES");
            encryptText = Base64.decodeBase64(text);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            encryptedString = new String(cipher.doFinal(encryptText));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }*/
    
	@Override
	public void sendMail(String receiver, String message) {
		
		Properties props = new Properties();
		
		
		props.put("mail.smtp.host", getSetting("mail.smtp.host"));
		props.put("mail.smtp.socketFactory.port", getSetting("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", getSetting("mail.smtp.socketFactory.class"));
		props.put("mail.smtp.auth", getSetting("mail.smtp.auth"));
		props.put("mail.smtp.port", getSetting("mail.smtp.port"));
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getSetting("username"),getSetting("password"));
				}
			});
 
		try {
 
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("polatkoksal50@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			msg.setSubject("Test");
			msg.setText(message);
 
			Transport.send(msg);
 
 
		} 
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		
		
	}


	private String getSetting(String dsc) {
		String value = null;
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select s from Settings s where s.key=:dsc");
		query.setParameter("dsc", dsc);
		query.setMaxResults(1);
		List<Settings> settings=query.getResultList();
		value = settings.get(0).getDescription();
		return value;
	}


	
	
}
