package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.Date;

public class DocumentUI implements Serializable{
	


	private static final long serialVersionUID = 1L;

	private Integer id;


	private Date date;


	private String link;

	private String name;


	private UserUI user;


	private VisitUI visit;

	
	public DocumentUI() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserUI getUser() {
		return this.user;
	}

	public void setUser(UserUI user) {
		this.user = user;
	}

	public VisitUI getVisit() {
		return this.visit;
	}

	public void setVisit(VisitUI visit) {
		this.visit = visit;
	}

}
