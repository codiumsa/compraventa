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

import compraventa.model.Compra;

/**
 * Capa de acceso a datos para la entidad {@link Compra}.
 * 
 * @author jorge
 */

@Stateless
@LocalBean
public class ComprasDAO implements DAO<Compra, Long> {

	@Inject
	EntityManager em;

	@Override
	public List<Compra> all() throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Compra> cq = cb.createQuery(Compra.class);
		Root<Compra> compra = cq.from(Compra.class);
		cq.select(compra);
		TypedQuery<Compra> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public void persist(Compra instance) throws Exception {
		em.persist(instance);
	}

	@Override
	public Compra update(Compra instance) throws Exception {
		Compra updated = em.merge(instance);
		em.flush();
		return updated;
	}

	@Override
	public void remove(Long id) throws Exception {
		Compra c = em.find(Compra.class, id);
		em.remove(c);
	}

	@Override
	public Compra find(Long id) throws Exception {
		return em.find(Compra.class, id);
	}

	@Override
	public Long count() throws Exception {
		return em.createNamedQuery("Compra.count", Long.class).getSingleResult();
	}

}
