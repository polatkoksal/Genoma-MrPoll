package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-08T00:13:19.950+0300")
@StaticMetamodel(Question.class)
public class Question_ {
	public static volatile SingularAttribute<Question, Integer> id;
	public static volatile SingularAttribute<Question, String> question;
	public static volatile SingularAttribute<Question, String> questionCode;
	public static volatile ListAttribute<Question, Answer> answers;
}
