package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-09T17:43:57.140+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> hospital;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, Document> documents;
	public static volatile ListAttribute<User, Patient> patients;
	public static volatile ListAttribute<User, UserRole> userRoles;
}
