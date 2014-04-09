package com.genoma.mrpoll.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Patient;
import com.genoma.mrpoll.domain.Question;
import com.genoma.mrpoll.domain.User;
import com.genoma.mrpoll.domain.Visit;
import com.genoma.mrpoll.uihelper.AnswerUI;
import com.genoma.mrpoll.uihelper.EditVisitData;
import com.genoma.mrpoll.uihelper.PatientUI;
import com.genoma.mrpoll.uihelper.SearchResultData;
import com.genoma.mrpoll.uihelper.VisitUI;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PatientServiceImpl extends RemoteServiceServlet implements
		PatientService {

	private static final long serialVersionUID = -5024300527855702085L;

	@Override
	public Boolean saveEditVisitData(EditVisitData editVisitData) {

		Boolean result = true;
		EntityManager em = EMF.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();

			HttpSession session = this.getThreadLocalRequest().getSession();
			User user = (User) session.getAttribute("loginUser");
			Query query = em.createQuery("select u from User u where u.id=:id");
			query.setParameter("id", user.getId());
			List<User> users = query.getResultList();
			// FIXME: Eger birden buyuk bir rakam yada 0 sa exception atılacak.
			// throw new RuntimeException("Mesaj");

			User editUser = users.get(0);
			Patient patient = null;
			if (editVisitData.getPatient().getId() != null) {
				patient = (Patient) em.find(Patient.class, editVisitData
						.getPatient().getId());
			} else {
				if (editVisitData.getPatient().getProtocolNo() != null) {
					Query query2 = em
							.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
					query2.setParameter("protocolNo", editVisitData
							.getPatient().getProtocolNo());
					List<Patient> patients = query2.getResultList();
					// FIXME: Eger birden buyuk bir rakam yada 0 sa exception
					// atılacak.
					// throw new RuntimeException("Mesaj");
					if (patients.size() > 0) {
						patient = patients.get(0);
					} else {
						patient = new Patient();
						patient.setUser(editUser);
					}
				}

			}
			Visit visit;
			if (editVisitData.getVisit().getId() != null) {
				visit = (Visit) em.find(Visit.class, editVisitData.getVisit()
						.getId());
				List<Answer> results = visit.getAnswers();
				for (Answer ans : results) {
					ans.getQuestion().removeAnswer(ans);
					em.remove(ans);
				}
				visit.getAnswers().clear();
			} else {
				visit = new Visit();
			}

			try {
				Integer patientId = patient.getId();
				Integer visitId = visit.getId();
				BeanUtils.copyProperties(patient, editVisitData.getPatient());
				BeanUtils.copyProperties(visit, editVisitData.getVisit());
				patient.setId(patientId);
				visit.setId(visitId);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw e1;
			}
			patient = em.merge(patient);
			visit.setPatient(patient);
			visit = em.merge(visit);
			patient.addVisit(visit);

			// DELETE ALL PRECIOUS ANSWERS OF VISIT.

			List<Answer> answers = new ArrayList<Answer>();
			for (AnswerUI answerUI : editVisitData.getAnswers()) {
				if (answerUI != null) {
					Answer answer = new Answer();
					answer.setAnswerValue(answerUI.getAnswerValue());
					Query query1 = em
							.createQuery("select q from Question q where q.questionCode=:questionCode");
					query1.setParameter("questionCode",
							answerUI.getQuestionCode());
					List<Question> questions = query1.getResultList();
					// FIXME : Throw exception
					if (!questions.isEmpty()) {
						questions.get(0).addAnswer(answer);
						visit.addAnswer(answer);
						answers.add(answer);
						em.persist(answer);
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return result;
	}

	public List<SearchResultData> searchVisit(Integer selection, String search) {

		EntityManager em = EMF.getEntityManager();

		List<SearchResultData> searchResultDatas = new ArrayList<SearchResultData>();
		List<Patient> patients = new ArrayList<Patient>();
		List<Visit> visits = new ArrayList<Visit>();

		switch (selection) {
		case 0:
			Query query = em
					.createQuery("select p from Patient p where p.nameSurname like :search");
			query.setParameter("search", search + "%");
			patients = query.getResultList();

			for (Patient patient : patients) {
				visits = patient.getVisits();
				for (Visit visit : visits) {
					SearchResultData searchResultData = new SearchResultData();
					searchResultData.setProtocolNo(patient.getProtocolNo());
					searchResultData.setNamaSurname(patient.getNameSurname());
					searchResultData.setPatientId(patient.getId());
					searchResultData.setHospital(visit.getHospital());
					searchResultData.setDate(visit.getDate());
					searchResultData.setAge(visit.getAge());
					searchResultData.setVisitId(visit.getId());
					searchResultDatas.add(searchResultData);
				}
			}
			break;
		case 1:
			Query query1 = em
					.createQuery("select v from Visit v where v.patient.protocolNo=:search");
			query1.setParameter("search", search);
			visits = query1.getResultList();

			for (Visit visit : visits) {
				SearchResultData searchResultData = new SearchResultData();
				searchResultData.setProtocolNo(visit.getPatient()
						.getProtocolNo());
				searchResultData.setNamaSurname(visit.getPatient()
						.getNameSurname());
				searchResultData.setPatientId(visit.getPatient().getId());
				searchResultData.setHospital(visit.getHospital());
				searchResultData.setDate(visit.getDate());
				searchResultData.setAge(visit.getAge());
				searchResultData.setVisitId(visit.getId());
				searchResultDatas.add(searchResultData);
			}
			break;
		case 2:

			break;
		case 3:

			break;
		}

		return searchResultDatas;
	}

	@Override
	public PatientUI getPatientFromDB(String protocolNo) {

		EntityManager em = EMF.getEntityManager();
		em.clear();
		Query query = em
				.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
		query.setParameter("protocolNo", protocolNo);
		List<Patient> patients = query.getResultList();
		PatientUI patientUI = new PatientUI();

		if (!patients.isEmpty()) {
			try {
				BeanUtils.copyProperties(patientUI, patients.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return patientUI;
	}

	@Override
	public EditVisitData getEditVisitData(SearchResultData searchResultData) {

		EditVisitData editVisitData = new EditVisitData();
		EntityManager em = EMF.getEntityManager();

		Query query1 = em
				.createQuery("select v from Visit v where v.id=:visitId");
		query1.setParameter("visitId", searchResultData.getVisitId());
		List<Visit> visits = query1.getResultList();
		Visit v = visits.get(0);
		Patient p = v.getPatient();
		List<Answer> answers = v.getAnswers();

		try {
			PatientUI patientUI = new PatientUI();
			VisitUI visitUI = new VisitUI();
			List<AnswerUI> answersUI = new ArrayList<AnswerUI>();

			BeanUtils.copyProperties(patientUI, p);
			BeanUtils.copyProperties(visitUI, v);
			for (Answer answer : answers) {
				AnswerUI answerUI = new AnswerUI();
				answerUI.setAnswerValue(answer.getAnswerValue());
				answerUI.setQuestionCode(answer.getQuestion().getQuestionCode());
				answersUI.add(answerUI);
			}

			editVisitData.setPatient(patientUI);
			editVisitData.setVisit(visitUI);
			editVisitData.setAnswers(answersUI);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return editVisitData;
	}

	@Override
	public Boolean deleteVisit(SearchResultData searchResultData) {

		Boolean result = false;
		EntityManager em = EMF.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Query query1 = em
					.createQuery("select v from Visit v where v.id=:visitId");
			query1.setParameter("visitId", searchResultData.getVisitId());
			List<Visit> visits = query1.getResultList();
			Visit v = visits.get(0);

			List<Answer> answers = v.getAnswers();
			for (Answer ans : answers) {
				v.removeAnswer(ans);
				ans.getQuestion().removeAnswer(ans);
				em.remove(ans);
			}
			Patient p = v.getPatient();
			p.removeVisit(v);
			em.remove(v);

			// FIXME: Kayhan Burası çalışmıyor. d
			List<Visit> visits2 = p.getVisits();
			if (visits2.size() == 0) {
				p.getUser().removePatient(p);
				em.remove(p);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		return result;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Override public Boolean savePatient(PatientUI patientUi) {
	 * 
	 * Boolean result = true; Patient patient = new Patient(); try {
	 * BeanUtils.copyProperties(patient, patientUi); } catch
	 * (IllegalAccessException e1) { e1.printStackTrace(); } catch
	 * (InvocationTargetException e1) { e1.printStackTrace(); } HttpSession
	 * session= this.getThreadLocalRequest().getSession(); User user = (User)
	 * session.getAttribute("loginUser");
	 * patient.setCreatedUserId(user.getId()); factory = EMF.get();
	 * EntityManager em = factory.createEntityManager(); Query query =
	 * em.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
	 * query.setParameter("protocolNo", patient.getProtocolNo()); List<Patient>
	 * patients = query.getResultList();
	 * 
	 * if(!patients.isEmpty()){ session.setAttribute("patient",
	 * patients.get(0)); result = false; }
	 * 
	 * if(result){ em.getTransaction().begin(); patient=em.merge(patient);
	 * em.getTransaction().commit(); em.close(); session.setAttribute("patient",
	 * patient); }
	 * 
	 * return result;
	 * 
	 * }
	 * 
	 * @Override public Boolean updatePatient() {
	 * 
	 * Boolean result = true;
	 * 
	 * HttpSession session= this.getThreadLocalRequest().getSession(); Patient
	 * patient = (Patient) session.getAttribute("patient");
	 * 
	 * 
	 * factory = EMF.get(); EntityManager em = factory.createEntityManager();
	 * Query query =
	 * em.createQuery("select p from Patient p where p.protocolNo=:protocolNo");
	 * query.setParameter("protocolNo", patient.getProtocolNo()); List<Patient>
	 * patients = query.getResultList(); Patient findPatient =
	 * em.find(Patient.class, patient.getId());
	 * 
	 * if(!patients.isEmpty()){ if(patients.get(0).getId() != patient.getId()){
	 * result = false; } }
	 * 
	 * 
	 * if(result){ em.getTransaction().begin();
	 * findPatient.setProtocolNo(patient.getProtocolNo());
	 * findPatient.setNameSurname(patient.getNameSurname());
	 * findPatient.setAge(patient.getAge());
	 * findPatient.setGender(patient.getGender()); patient =
	 * em.merge(findPatient); em.getTransaction().commit(); em.close();
	 * session.setAttribute("patient", patient); }
	 * 
	 * return result; }
	 * 
	 * 
	 * @Override public void createVisit() { HttpSession session=
	 * this.getThreadLocalRequest().getSession(); Patient patient = (Patient)
	 * session.getAttribute("patient");
	 * 
	 * Visit visit = new Visit(); //DateFormat dateFormat = new
	 * SimpleDateFormat("dd/MM/yyyy");
	 * 
	 * 
	 * factory = EMF.get(); EntityManager em = factory.createEntityManager();
	 * patient =em.find(Patient.class, patient.getId()); Date date = new Date();
	 * visit.setDate(date); visit.setPatient(patient);
	 * em.getTransaction().begin(); visit = em.merge(visit);
	 * em.getTransaction().commit(); em.close();
	 * session.removeAttribute("visit"); session.setAttribute("visit", visit); }
	 * 
	 * @Override public Boolean updateVisit() {
	 * 
	 * HttpSession session= this.getThreadLocalRequest().getSession(); Visit
	 * visit = (Visit) session.getAttribute("visit");
	 * 
	 * factory = EMF.get(); EntityManager em = factory.createEntityManager();
	 * Visit findVisit = em.find(Visit.class, visit.getId());
	 * 
	 * em.getTransaction().begin(); findVisit.setDate(visit.getDate());
	 * findVisit.setHospital(visit.getHospital());
	 * findVisit.setEthic(visit.getEthic()); findVisit.setNote(visit.getNote());
	 * visit = em.merge(findVisit); em.getTransaction().commit(); em.close();
	 * session.removeAttribute("visit"); session.setAttribute("visit", visit);
	 * return true; }
	 * 
	 * @Override public Boolean getVisitFromDB() { Boolean result = true;
	 * 
	 * HttpSession session= this.getThreadLocalRequest().getSession(); Patient
	 * patientSession = (Patient) session.getAttribute("patient");
	 * 
	 * factory = EMF.get(); EntityManager em = factory.createEntityManager();
	 * Query query = em.createQuery(
	 * "select v from Visit v where v.patient.id = :patientId order by v.date desc, v.id desc"
	 * ); query.setParameter("patientId", patientSession.getId());
	 * query.setMaxResults(1); List<Visit> visits = query.getResultList();
	 * 
	 * session.setAttribute("visit", visits.get(0));
	 * 
	 * return result; }
	 * 
	 * @Override public void savePatientToSession(PatientUI patientUi) { Patient
	 * patient = new Patient(); try { BeanUtils.copyProperties(patient,
	 * patientUi); } catch (IllegalAccessException e) { e.printStackTrace(); }
	 * catch (InvocationTargetException e) { e.printStackTrace(); } HttpSession
	 * session= this.getThreadLocalRequest().getSession(); User user = (User)
	 * session.getAttribute("loginUser"); Patient temp = (Patient)
	 * session.getAttribute("patient"); patient.setCreatedUserId(user.getId());
	 * patient.setId(temp.getId()); session.removeAttribute("patient");
	 * session.setAttribute("patient", patient); }
	 * 
	 * 
	 * @Override public PatientUI getPatientFromSession() { HttpSession session=
	 * this.getThreadLocalRequest().getSession(); Patient patient = (Patient)
	 * session.getAttribute("patient"); PatientUI patientUi = new PatientUI();
	 * try { BeanUtils.copyProperties(patientUi, patient); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (InvocationTargetException e) { e.printStackTrace(); } return patientUi;
	 * }
	 * 
	 * @Override public UserUI getUserFromSession() { HttpSession session=
	 * this.getThreadLocalRequest().getSession(); User user = (User)
	 * session.getAttribute("loginUser"); UserUI userUi = new UserUI(); try {
	 * BeanUtils.copyProperties(userUi, user); } catch (IllegalAccessException
	 * e) { e.printStackTrace(); } catch (InvocationTargetException e) {
	 * e.printStackTrace(); }
	 * 
	 * return userUi; }
	 * 
	 * @Override public void saveAnswersToSession(List<Answer> answers) {
	 * if(answers==null){ return; } HttpSession session =
	 * this.getThreadLocalRequest().getSession(); List<Answer>
	 * loki=(List<Answer>) session.getAttribute("answers"); if (loki!=null){
	 * session.removeAttribute("answers"); } else{ loki=new
	 * LinkedList<Answer>(); } loki = mergeAnswers(loki, answers);
	 * session.setAttribute("answers", loki);
	 * 
	 * }
	 * 
	 * @Override public List<Answer> getAnswersFromSession() { HttpSession
	 * session = this.getThreadLocalRequest().getSession(); List<Answer>
	 * result=(List<Answer>) session.getAttribute("answers"); return result; }
	 * 
	 * 
	 * 
	 * 
	 * @Override public void saveVisitToSession(VisitUI visitUi) { Visit visit =
	 * new Visit(); try { BeanUtils.copyProperties(visit, visitUi); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (InvocationTargetException e) { e.printStackTrace(); } HttpSession
	 * session= this.getThreadLocalRequest().getSession(); Visit temp = (Visit)
	 * session.getAttribute("visit"); Patient patient = (Patient)
	 * session.getAttribute("patient"); visit.setId(temp.getId());
	 * visit.setPatient(patient); session.removeAttribute("visit");
	 * session.setAttribute("visit", visit); }
	 * 
	 * 
	 * @Override public VisitUI getVisitFromSession() { HttpSession session=
	 * this.getThreadLocalRequest().getSession(); Visit visit =
	 * (Visit)session.getAttribute("visit"); VisitUI visitUi = new VisitUI();
	 * try { BeanUtils.copyProperties(visitUi, visit); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (InvocationTargetException e) { e.printStackTrace(); } return visitUi; }
	 * 
	 * public List<Answer> mergeAnswers(List<Answer> target, List<Answer> list
	 * ){ for(Answer w: list){ for(Answer a:target){
	 * if(a.getBelongsQuestionId().equals(w.getBelongsQuestionId())){
	 * target.remove(a); break; } } } target.addAll(list); return target; }
	 * 
	 * @Override public void close() {
	 * 
	 * HttpSession session= this.getThreadLocalRequest().getSession(); Patient
	 * patient = (Patient)session.getAttribute("patient"); Visit visit =
	 * (Visit)session.getAttribute("visit");
	 * 
	 * factory = EMF.get(); EntityManager em = factory.createEntityManager();
	 * 
	 * Query query1 =
	 * em.createQuery("select a from Answer a where a.visit.id=:visitId");
	 * query1.setParameter("visitId", visit.getId()); List<Answer> answers =
	 * query1.getResultList();
	 * 
	 * if(answers.isEmpty()){ Visit tempVisit = em.find(Visit.class,
	 * visit.getId()); em.getTransaction().begin(); em.remove(tempVisit);
	 * em.getTransaction().commit();
	 * 
	 * }
	 * 
	 * Query query2 =
	 * em.createQuery("select v from Visit v where v.patient.id=:patientId");
	 * query2.setParameter("patientId", patient.getId()); List<Visit> visits =
	 * query2.getResultList();
	 * 
	 * if(visits.isEmpty()){ Patient tempPatient = em.find(Patient.class,
	 * patient.getId()); em.getTransaction().begin(); em.remove(tempPatient);
	 * em.getTransaction().commit(); em.close(); }
	 * 
	 * session.removeAttribute("patient"); session.removeAttribute("visit");
	 * 
	 * }
	 */

}
