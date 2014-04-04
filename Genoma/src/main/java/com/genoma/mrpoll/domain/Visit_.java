package com.genoma.mrpoll.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-04T20:08:47.957+0300")
@StaticMetamodel(Visit.class)
public class Visit_ {
	public static volatile SingularAttribute<Visit, Integer> id;
	public static volatile SingularAttribute<Visit, String> age;
	public static volatile SingularAttribute<Visit, Date> date;
	public static volatile SingularAttribute<Visit, Boolean> ethic;
	public static volatile SingularAttribute<Visit, String> hospital;
	public static volatile SingularAttribute<Visit, String> note;
	public static volatile ListAttribute<Visit, Answer> answers;
	public static volatile ListAttribute<Visit, Document> documents;
	public static volatile SingularAttribute<Visit, Patient> patient;
}
