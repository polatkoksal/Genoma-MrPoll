package com.genoma.mrpoll.server;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.genoma.mrpoll.client.PatientService;
import com.genoma.mrpoll.client.PatientServiceAsync;
import com.genoma.mrpoll.domain.Answer;
import com.genoma.mrpoll.domain.Question;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PatientServiceImpl extends RemoteServiceServlet implements PatientService {
	private static final long serialVersionUID = -5024300527855702085L;

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
	public
	void saveAllAnswers(List<Answer> answers) {
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
	public List<Answer> getAllAnswers() {
		HttpSession session = this.getThreadLocalRequest().getSession();
		List<Answer> result=(List<Answer>) session.getAttribute("questions");
		return result;
	}
		
}
