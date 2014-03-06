package com.genoma.mrpoll.client;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	 private static final EntityManagerFactory emfInstance =
		        Persistence.createEntityManagerFactory("Genoma");
	  
	  private EMF() {}

	  public static EntityManagerFactory get() {
		  return emfInstance;
	  }

}
