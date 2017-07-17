package compraventa.dao;

import java.util.Calendar;
import java.util.Date;
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
import compraventa.model.CompraDetalle;
import compraventa.model.Compra_;

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

	public List<Compra> allBeforeDate(Date date) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Compra> cq = cb.createQuery(Compra.class);
		Root<Compra> compra = cq.from(Compra.class);
		cq.select(compra);
		cq.where(cb.lessThanOrEqualTo(compra.get(Compra_.fecha), date));
		TypedQuery<Compra> q = em.createQuery(cq);
		return q.getResultList();
	}

	/**
	 * Retorna las compras registradas en el rango de fechas dado como
	 * parámetro.
	 * 
	 * @param desde
	 * @param hasta
	 * @return
	 * @throws Exception
	 */
	public List<Compra> allInDateRange(Date desde, Date hasta) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Compra> cq = cb.createQuery(Compra.class);
		Root<Compra> compra = cq.from(Compra.class);
		cq.select(compra);
		// sumamos un día a hasta para que pueda ser inclusivo el rango de
		// fechas.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(hasta);
		calendar.add(Calendar.DATE, 1);
		cq.where(cb.and(cb.greaterThanOrEqualTo(compra.get(Compra_.fecha), desde),
				cb.lessThanOrEqualTo(compra.get(Compra_.fecha), calendar.getTime())));
		TypedQuery<Compra> q = em.createQuery(cq);
		return q.getResultList();
	}

	public List<CompraDetalle> getDetalles(Long compraId) throws Exception {
		Compra c = em.find(Compra.class, compraId);
		List<CompraDetalle> detalles = c.getDetalles();

		for (CompraDetalle cd : detalles) {
			em.detach(cd);
		}
		em.detach(c);
		return detalles;
	}

}
