package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-09T17:43:57.147+0300")
@StaticMetamodel(UserRole.class)
public class UserRole_ {
	public static volatile SingularAttribute<UserRole, Integer> id;
	public static volatile SingularAttribute<UserRole, Role> role;
	public static volatile SingularAttribute<UserRole, User> user;
}
