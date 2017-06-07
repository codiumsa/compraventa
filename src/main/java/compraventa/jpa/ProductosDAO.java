package compraventa.jpa;

import javax.persistence.EntityManager;

import compraventa.Producto;

/**
 * Capa de acceso a datos, que utiliza JPA, para la tabla producto.
 * 
 * @author jorge
 */
public class ProductosDAO {
	
	private EntityManager em;
	
	public ProductosDAO() {
		em = PersistenceManager.INSTANCE.getEntityManager();
	}

	public void persist(Producto p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void close() {
		em.close();
	}
}
