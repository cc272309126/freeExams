package com.provas.util;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.examserver.fwk.configuration.BaseEntityManagerFactory;

/**
 *
 * TODO - Preencher javaDoc
 *
 * @author tiagok
 */
@ApplicationScoped
public class ManagerFactory implements BaseEntityManagerFactory {

	@PersistenceContext(unitName = "persistenceUnit")
	private static EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}