package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-01T16:51:37.087+0300")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Integer> id;
	public static volatile SingularAttribute<Patient, Integer> age;
	public static volatile SingularAttribute<Patient, String> gender;
	public static volatile SingularAttribute<Patient, String> nameSurname;
	public static volatile SingularAttribute<Patient, String> protocolNo;
	public static volatile SingularAttribute<Patient, User> user;
	public static volatile ListAttribute<Patient, Visit> visits;
}
