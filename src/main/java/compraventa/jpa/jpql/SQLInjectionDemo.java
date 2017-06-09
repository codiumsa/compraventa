package compraventa.jpa.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import compraventa.Producto;
import compraventa.Producto_;

public class SQLInjectionDemo {

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
		
		unsecureSQL(em, "' OR '1'='1");
		secureSQL(em, "' OR '1'='1");

		em.close();
	}

	@SuppressWarnings("unchecked")
	private static void unsecureSQL(EntityManager em, String codigo) {
		System.out.println("Ejecutando método: unsecureSQL");
		String sql = "SELECT p FROM Producto p where p.codigo ='" + codigo + "'";
		List<Producto> productos = em.createQuery(sql).getResultList();
		System.out.println("Listado de productos");

		for (Producto pro : productos) {
			System.out.println(pro);
		}
		
		// Para prevenir usando JPQL, simplemente usar "bound parameters".
		// List<Producto> productos = em.createQuery("SELECT p FROM Producto p where p.codigo = :codigo").setParamter("codigo", codigo).getResultList();
	}

	private static void secureSQL(EntityManager em, String codigo) {
		System.out.println("Ejecutando método: secureSQL");
		// consulta tipada utilizando Criteria Query.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> producto = cq.from(Producto.class);
		cq.select(producto);
		cq.where(cb.equal(producto.get(Producto_.codigo), codigo));
		TypedQuery<Producto> q = em.createQuery(cq);
		List<Producto> productos = q.getResultList();

		System.out.println("Listado de productos");

		for (Producto pro : productos) {
			System.out.println(pro);
		}
	}
}
