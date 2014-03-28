package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-28T14:00:36.827+0200")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, UserRole> userRoles;
}
