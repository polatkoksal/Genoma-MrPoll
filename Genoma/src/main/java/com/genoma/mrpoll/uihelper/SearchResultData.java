package com.genoma.mrpoll.uihelper;

import java.io.Serializable;
import java.util.Date;

public class SearchResultData implements Serializable {

	private static final long serialVersionUID = 1L;

	public String protocolNo;

	public String namaSurname;

	public String age;

	public Date date;

	public String hospital;

	public Integer patientId;

	public Integer visitId;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getNamaSurname() {
		return namaSurname;
	}

	public void setNamaSurname(String namaSurname) {
		this.namaSurname = namaSurname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

}
