package compraventa.jpa;

import javax.persistence.EntityManager;

import compraventa.Producto;

/**
 * Clase de ejemplo que muestra cómo obtener una instancia del
 * {@link EntityManager} y persistir un {@link Producto}.
 * 
 * @author jorge
 */
public class TestJPA {

	public static void main(String[] args) {
		Producto p = new Producto();
		p.setCodigo("BAN01");
		p.setNombre("Banana de Oro");
		p.setPrecio(2000L);
		p.setExistencia(100);
		
		System.out.println("Iniciamos el registro del producto...");

		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();

		em.persist(p);

		em.getTransaction().commit();
		System.out.println("Registro creado!");
		System.out.println(p);
		em.close();
	}
}
