package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T14:00:36.811+0200")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Integer> id;
	public static volatile SingularAttribute<Patient, Integer> age;
	public static volatile SingularAttribute<Patient, Integer> createdUserId;
	public static volatile SingularAttribute<Patient, String> gender;
	public static volatile SingularAttribute<Patient, String> nameSurname;
	public static volatile SingularAttribute<Patient, String> protocolNo;
}
