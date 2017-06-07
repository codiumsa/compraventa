package compraventa.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase helper para acceder a una instancia del {@link EntityManager}.
 * 
 * @author jorge
 */
public enum PersistenceManager {
	INSTANCE;

	private EntityManagerFactory emf;

	private PersistenceManager() {
		emf = Persistence.createEntityManagerFactory("compraventa");
	}

	/**
	 * Retorna una instancia de {@link EntityManager}
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Cierra el {@link EntityManagerFactory}
	 */
	public void close() {
		emf.close();
	}
}
