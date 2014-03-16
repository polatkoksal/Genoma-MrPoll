package com.genoma.mrpoll.server;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.genoma.mrpoll.client.EMF;
import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Question;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.UserUI;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PatientServiceImpl extends RemoteServiceServlet implements PatientService {
	
	private static final long serialVersionUID = -5024300527855702085L;
	private EntityManagerFactory factory;

	 public void saveAnswer(Answer answer) {
		if(answer==null){return;}
		HttpSession session = this.getThreadLocalRequest().getSession();
		List<Answer> loki=(List<Answer>) session.getAttribute("questions");
		if (loki!=null){
			session.removeAttribute("questions");
		}
		else{
			loki=new LinkedList<Answer>();
		}
		loki.add(answer);//add RemoveDuplicates
		session.setAttribute("questions", loki);
	}

	@Override
	public void saveAnswers(List<Answer> answers) {
		if(answers==null){return;}
		HttpSession session = this.getThreadLocalRequest().getSession();
		List<Answer> loki=(List<Answer>) session.getAttribute("questions");
		if (loki!=null){
			session.removeAttribute("questions");
		}
		else{
			loki=new LinkedList<Answer>();
		}
		loki.addAll(answers);//add RemoveDuplicates
		session.setAttribute("questions", loki);
		
	}

	@Override
	public List<Answer> getAnswers() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		List<Answer> result=(List<Answer>) session.getAttribute("questions");
		return result;
	}

	@Override
	public void savePatientToSession(Patient patient) {		
		HttpSession session= this.getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("loginUser");
		session.removeAttribute("patient");
		patient.setCreatedUserId(user.getId());
		session.setAttribute("patient", patient);
	}

	@Override
	public void saveVisitToSession(Visit visit) {
		HttpSession session= this.getThreadLocalRequest().getSession();
		session.removeAttribute("visit");
		session.setAttribute("visit", visit);
	}

	@Override
	public Boolean savePatient(Patient patient) {
		
		Boolean result = true;
		HttpSession session= this.getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("loginUser");
		patient.setCreatedUserId(user.getId());
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select t from Patient t");
		List<Patient> patients = query.getResultList();
		for(Patient p : patients){
			if(p.getProtocolNo().equals(patient.getProtocolNo())){
				result = false;
				break;
			}
			
		}
		if(result){
			em.getTransaction().begin();
			em.persist(patient);
			em.getTransaction().commit();
			em.close();
		}
		
		savePatientToSession(patient);
		return result;
			
	}
	
	@Override
	
	
	public Boolean updatePatient(Patient patient) {
		
		Boolean result = true;
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select t from Patient t");
		List<Patient> patients = query.getResultList();
		Patient newPatient = em.find(Patient.class, getPatientFromSession().getId());
		for(Patient p : patients){
			if(p.getProtocolNo().equals(newPatient)){
				result = false;
				break;
			}
		}
		if(result){
			em.getTransaction().begin();
			newPatient.setProtocolNo(patient.getProtocolNo());
			newPatient.setNamesurname(patient.getNamesurname());
			newPatient.setAge(patient.getAge());
			newPatient.setGender(patient.getGender());
			//newPatient.setDate(patient.getDate());
			//newPatient.setHospital(patient.getHospital);
			em.getTransaction().commit();
			em.close();
		}
		
		
		return null;
	}

	
	@Override
	public Patient getPatientFromSession() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		return patient;
	}
	
	@Override
	public UserUI getUserFromSession() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("loginUser");
		UserUI userUi = new UserUI();
		try {
			BeanUtils.copyProperties(userUi, user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return userUi;
	}
	
	@Override
	public Visit getVisitFromSession() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Visit visit = (Visit)session.getAttribute("loginUser");
		return visit;
	}


	@Override
	public void createVisit() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		Visit visit = new Visit();
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		visit.setPatient(patient);
		visit.setDate(date);
		
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(visit);
		em.getTransaction().commit();
		em.close();
		
		session.setAttribute("visit", visit);
	}

	
	

	
	
		
}
