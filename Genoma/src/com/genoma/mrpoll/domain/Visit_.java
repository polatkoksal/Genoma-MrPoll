package com.genoma.mrpoll.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T15:36:34.881+0200")
@StaticMetamodel(Visit.class)
public class Visit_ {
	public static volatile SingularAttribute<Visit, Integer> id;
	public static volatile SingularAttribute<Visit, Date> date;
	public static volatile SingularAttribute<Visit, Boolean> ethic;
	public static volatile SingularAttribute<Visit, String> hospital;
	public static volatile SingularAttribute<Visit, String> note;
	public static volatile SingularAttribute<Visit, Patient> patient;
}
