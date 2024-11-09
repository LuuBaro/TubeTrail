package com.asm.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("ASM_1");
		}
		EntityManager eManager = factory.createEntityManager();

		return eManager;
	}

	public static void shutdown() {
		if (factory == null || !factory.isOpen()) {
			factory.close();

		}
		factory = null;
	}
}
