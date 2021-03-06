package compraventa.dao;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Default producer para el {@link EntityManager}.
 * 
 * @author jorge
 *
 */
public class EntityManagerProducer {

	@Produces
	@PersistenceContext
	private EntityManager em;

	public EntityManagerProducer() {

	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
}
