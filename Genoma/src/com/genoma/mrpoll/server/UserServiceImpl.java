package com.genoma.mrpoll.server;

import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
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
		for(User u:users)
			System.out.println(u.getId()+u.getUsername()+u.getPassword());
		return users;
	}
	
	
	@Override
	public Boolean addUser(UserUI userUi) {
		
		Boolean result = true;
		User user = convertToUser(userUi);
		List<User> users = getAll();
		for(User tempUser : users){
			if(tempUser.getUsername().equals(userUi.getUsername())){
				result = false;
			}
		}
		if(result){
			factory = EMF.get();
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
		}
		return result;
		
	}

	
	public Boolean deleteUser(UserUI userUi){
		User user = convertToUser(userUi);
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
		HttpSession session = this.getThreadLocalRequest().getSession();
		User loginUser = (User) session.getAttribute(sessionParam);
		User user =convertToUser(userUi);
		user.setId(loginUser.getId());
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		User tempUser = em.find(User.class, user.getId());
		List<User> users = getAll();
		for(User searchUser : users){
			if(searchUser.getUsername().equals(user.getUsername()) && searchUser.getId()!=user.getId()){
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
			System.out.println("Equity of the ids :" + (user.getId()==tempUser.getId()));
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
		UserUI newUser = convertToUserUi(sessionUser);
		
		/*try {
			BeanUtils.copyProperties(newUser, result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/ 
		return newUser;
	}
	
	
	
	
	public Boolean isAdmin(){
		Boolean result = false;
		HttpSession session = this.getThreadLocalRequest().getSession();
		User user = (User)session.getAttribute("loginUser");
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select ur from UserRole ur where ur.user.id = :param");
		query.setParameter("param", user.getId());
		List<UserRole> userRoles = query.getResultList();
		if(!userRoles.isEmpty() && userRoles.get(0).getRole().getName().equals("admin")){
			result = true; 
		}
		return result;
		
	}
	
	

	public List<UserUI> searchUser(String coulmn,String name){
		//putSession(String.class, "currentSearch", name);
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute("currentSearch", name);
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select u from User u where u."+coulmn+"=:name");
		query.setParameter("name", name);
		List<User> users=query.getResultList();
		List<UserUI> usersUI = new ArrayList<UserUI>();
		if(!users.isEmpty()){
			for(User user : users){
				usersUI.add(convertToUserUi(user));
			}
		}
		return usersUI;
	}
	
	
	
	public void putSessionUser(String param, UserUI userUi){
		User user = convertToUser(userUi);
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute(param, user);
		
		
	}
	
	public void putSessionString(String param, String string){
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute(param, string);
		
	}
	
	
	public UserUI convertToUserUi(User user){
		UserUI result = new UserUI();
		result.setId(user.getId());
		result.setName(user.getName());
		result.setPassword(user.getPassword());
		result.setUsername(user.getUsername());
		result.setSurname(user.getSurname());
		result.setPhone(user.getPhone());
		result.setEmail(user.getEmail());
		result.setHospital(user.getHospital());
		return result;
	}
	
	public User convertToUser(UserUI userUi){
		User result = new User();
		result.setId(userUi.getId());
		result.setName(userUi.getName());
		result.setPassword(userUi.getPassword());
		result.setUsername(userUi.getUsername());
		result.setSurname(userUi.getSurname());
		result.setPhone(userUi.getPhone());
		result.setEmail(userUi.getEmail());
		result.setHospital(userUi.getHospital());
		return result;
		
		
	}
	
	
	
	public User getUser(String userName){
		User result = null;
		return result;
		
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
	
	
}
