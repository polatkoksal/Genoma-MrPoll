package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T15:36:54.047+0200")
@StaticMetamodel(Answer.class)
public class Answer_ {
	public static volatile SingularAttribute<Answer, Integer> id;
	public static volatile SingularAttribute<Answer, String> answer;
	public static volatile SingularAttribute<Answer, Question> question;
	public static volatile SingularAttribute<Answer, Integer> answeredVisitId;
}
