package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.Date;

public class VisitUI implements Serializable{

	private static final long serialVersionUID = 1L;



	private Integer id;

	private Date date;
	
	private String age;

	private Boolean ethic;

	private String hospital;

	private String note;

	public VisitUI() {
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
	
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Boolean getEthic() {
		return this.ethic;
	}

	public void setEthic(Boolean ethic) {
		this.ethic = ethic;
	}

	public String getHospital() {
		return this.hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
