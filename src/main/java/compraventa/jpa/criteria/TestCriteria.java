package compraventa.jpa.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import compraventa.Producto;
import compraventa.jpa.jpql.PersistenceManager;

/**
 * Clase que muestra utilización básica de Criteria Query.
 * 
 * @author jorge
 */
public class TestCriteria {

	public static void main(String[] args) {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
		Producto p = new Producto();
		p.setCodigo("BAN01");
		p.setNombre("Banana de Oro");
		p.setPrecio(2000L);
		p.setExistencia(100);
		
		System.out.println("Iniciamos el registro del producto...");

		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		System.out.println("Registro creado!");
		
		// consulta tipada utilizando Criteria Query.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> producto = cq.from(Producto.class);
		cq.select(producto);
		TypedQuery<Producto> q = em.createQuery(cq);
		List<Producto> productos = q.getResultList();

		System.out.println("Listado de productos");

		for (Producto pro: productos) {
			System.out.println(pro);
		}
		em.close();
	}

}
