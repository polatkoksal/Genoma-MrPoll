package com.genoma.mrpoll.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-04-04T16:18:27.526+0300")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, UserRole> userRoles;
}
