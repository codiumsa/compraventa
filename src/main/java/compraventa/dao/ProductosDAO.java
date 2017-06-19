package compraventa.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import compraventa.model.Compra;
import compraventa.model.Producto;

public class ProductosDAO implements DAO<Producto, Long> {

	@Inject
	EntityManager em;

	@Override
	public List<Producto> all() throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> producto = cq.from(Producto.class);
		cq.select(producto);
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
		Compra c = em.find(Compra.class, id);
		em.remove(c);
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