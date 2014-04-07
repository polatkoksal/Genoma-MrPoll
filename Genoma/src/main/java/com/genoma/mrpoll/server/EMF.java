package com.genoma.mrpoll.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("Genoma");
	private static EntityManager em = null;

	private EMF() {
	}

	public static EntityManager getEntityManager() {
		if (em == null) {
			em = emfInstance.createEntityManager();
		} else {
			if (!em.isOpen()) {
				em = emfInstance.createEntityManager();
			}
		}
		return em;
	}

}
