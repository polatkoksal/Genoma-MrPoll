package com.genoma.mrpoll.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T14:00:36.796+0200")
@StaticMetamodel(Document.class)
public class Document_ {
	public static volatile SingularAttribute<Document, Integer> id;
	public static volatile SingularAttribute<Document, Integer> attachedVisitId;
	public static volatile SingularAttribute<Document, Integer> createsUserId;
	public static volatile SingularAttribute<Document, Date> date;
	public static volatile SingularAttribute<Document, String> link;
	public static volatile SingularAttribute<Document, String> name;
}
