package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-04T20:03:57.967+0300")
@StaticMetamodel(Answer.class)
public class Answer_ {
	public static volatile SingularAttribute<Answer, Integer> id;
	public static volatile SingularAttribute<Answer, String> answer;
	public static volatile SingularAttribute<Answer, Question> question;
	public static volatile SingularAttribute<Answer, Visit> visit;
}
