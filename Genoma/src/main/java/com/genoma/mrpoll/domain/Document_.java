package com.genoma.mrpoll.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-08T00:13:19.926+0300")
@StaticMetamodel(Document.class)
public class Document_ {
	public static volatile SingularAttribute<Document, Integer> id;
	public static volatile SingularAttribute<Document, Date> date;
	public static volatile SingularAttribute<Document, String> link;
	public static volatile SingularAttribute<Document, String> name;
	public static volatile SingularAttribute<Document, User> user;
	public static volatile SingularAttribute<Document, Visit> visit;
}
