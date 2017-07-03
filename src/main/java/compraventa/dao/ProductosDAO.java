package compraventa.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import compraventa.model.Producto;
import compraventa.model.Producto_;

@Stateless
@LocalBean
public class ProductosDAO implements DAO<Producto, Long> {

	@Inject
	EntityManager em;

	/**
	 * Listado de todos los elementos.
	 */
	@Override
	public List<Producto> all() throws Exception {
		return all(null);
	}

	/**
	 * Listado de elementos con posibilidad de filtrar por código.
	 * 
	 * @param codigo
	 * @param nombre
	 * @return
	 * @throws Exception
	 */
	public List<Producto> all(String codigo) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> producto = cq.from(Producto.class);
		cq.select(producto);

		if (codigo != null) {
			cq.where(cb.equal(producto.get(Producto_.codigo), codigo));
		}
		TypedQuery<Producto> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public void persist(Producto instance) throws Exception {
		em.persist(instance);
	}

	@Override
	public Producto update(Producto instance) throws Exception {
		Producto updated = em.merge(instance);
		em.flush();
		return updated;
	}

	@Override
	public void remove(Long id) throws Exception {
		Producto p = em.find(Producto.class, id);

		if (p == null) {
			throw new RuntimeException("No existe el elemento con ID: " + id);
		}
		em.remove(p);
	}

	@Override
	public Producto find(Long id) throws Exception {
		return em.find(Producto.class, id);
	}

	@Override
	public Long count() throws Exception {
		return em.createNamedQuery("Producto.count", Long.class).getSingleResult();
	}
}
