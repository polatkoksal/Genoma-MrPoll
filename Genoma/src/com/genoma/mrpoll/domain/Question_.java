package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T14:11:24.979+0200")
@StaticMetamodel(Question.class)
public class Question_ {
	public static volatile SingularAttribute<Question, Integer> id;
	public static volatile SingularAttribute<Question, String> question;
	public static volatile SingularAttribute<Question, String> questionCode;
	public static volatile SetAttribute<Question, Answer> answers;
}
