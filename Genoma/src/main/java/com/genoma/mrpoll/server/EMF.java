package com.genoma.mrpoll.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("Genoma");

	private EMF() {
	}

	public static EntityManager getEntityManager() {
		EntityManager em = emfInstance.createEntityManager();
		return em;
	}

}
