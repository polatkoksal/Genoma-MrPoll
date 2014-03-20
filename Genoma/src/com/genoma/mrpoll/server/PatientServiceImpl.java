package com.genoma.mrpoll.server;

import java.lang.reflect.InvocationTargetException;
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
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.UserUI;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PatientServiceImpl extends RemoteServiceServlet implements PatientService {
	
	private static final long serialVersionUID = -5024300527855702085L;
	private EntityManagerFactory factory;

	
	

	@Override
	public Boolean savePatient(PatientUI patientUi) {
		
		Boolean result = true;
		Patient patient = new Patient();
		try {
			BeanUtils.copyProperties(patient, patientUi);
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		HttpSession session= this.getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("loginUser");
		patient.setCreatedUserId(user.getId());
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
		query.setParameter("protocolNo", patient.getProtocolNo());
		List<Patient> patients = query.getResultList();
		
		if(!patients.isEmpty()){
			session.setAttribute("patient", patients.get(0));
			result = false;
		}
		
		if(result){
			em.getTransaction().begin();
			patient=em.merge(patient);
			em.getTransaction().commit();
			em.close();
			session.setAttribute("patient", patient);
		}
		
		return result;
			
	}
	
	@Override
	public Boolean updatePatient(PatientUI patientUi) {
		
		Boolean result = true;
		Patient patient = new Patient();
		
		try {
			BeanUtils.copyProperties(patient, patientUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		HttpSession session= this.getThreadLocalRequest().getSession();
		Patient temp = (Patient) session.getAttribute("patient");
		patient.setId(temp.getId());
		patient.setCreatedUserId(temp.getCreatedUserId());
		
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
		query.setParameter("protocolNo", patient.getProtocolNo());
		List<Patient> patients = query.getResultList();
		Patient findPatient = em.find(Patient.class, getPatientFromSession().getId());
		
		if(!patients.isEmpty()){
			if(patients.get(0).getId() != patient.getId()){
				result = false;
			}
		}
		
		
		if(result){
			em.getTransaction().begin();
			findPatient.setProtocolNo(patient.getProtocolNo());
			findPatient.setNameSurname(patient.getNameSurname());
			findPatient.setAge(patient.getAge());
			findPatient.setGender(patient.getGender());
			em.getTransaction().commit();
			em.close();
			session.setAttribute("patient", findPatient);
		}
		
		return result;
	}

	
	@Override
	public void createVisit() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		
		Visit visit = new Visit();
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		factory = EMF.get();
		EntityManager em = factory.createEntityManager();
		patient =em.find(Patient.class, patient.getId());
		Date date = new Date();	
		visit.setDate(date);
		visit.setPatient(patient);
		em.getTransaction().begin();
		em.persist(visit);
		em.getTransaction().commit();
		em.close();
		
		session.setAttribute("visit", visit);
	}
	
	
	@Override
	public void savePatientToSession(PatientUI patientUi) {
		Patient patient = new Patient();
		try {
			BeanUtils.copyProperties(patient, patientUi);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		HttpSession session= this.getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("loginUser");
		session.removeAttribute("patient");
		patientUi.setCreatedUserId(user.getId());
		session.setAttribute("patient", patient);
	}
	
	
	@Override
	public PatientUI getPatientFromSession() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Patient patient = (Patient) session.getAttribute("patient");
		PatientUI patientUi = new PatientUI();
		try {
			BeanUtils.copyProperties(patientUi, patient);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return patientUi;
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
	public void saveAnswersToSession(List<Answer> answers) {
		if(answers==null){
			return;
		}
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
	public List<Answer> getAnswersFromSession() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		List<Answer> result=(List<Answer>) session.getAttribute("questions");
		return result;
	}

	

	
	@Override
	public void saveVisitToSession(VisitUI visitUi) {
		Visit visit = new Visit();
		try {
			BeanUtils.copyProperties(visitUi, visit);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		HttpSession session= this.getThreadLocalRequest().getSession();
		session.removeAttribute("visit");
		session.setAttribute("visit", visit);
	}
	
	
	@Override
	public VisitUI getVisitFromSession() {
		HttpSession session= this.getThreadLocalRequest().getSession();
		Visit visit = (Visit)session.getAttribute("visit");
		VisitUI visitUi = new VisitUI();
		try {
			BeanUtils.copyProperties(visitUi, visit);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return visitUi;
	}

		
}
