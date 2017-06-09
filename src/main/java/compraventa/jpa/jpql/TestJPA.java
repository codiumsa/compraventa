package compraventa.jpa.jpql;

import java.util.Date;

import javax.persistence.EntityManager;

import compraventa.Compra;
import compraventa.CompraDetalle;
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
		
		Compra compra = new Compra();
		CompraDetalle detalle = new CompraDetalle();
		detalle.setCompra(compra);
		compra.getDetalles().add(detalle);
		
		detalle.setCantidad(10);
		detalle.setProducto(p);
		compra.setFecha(new Date());
		em.persist(compra);

		em.getTransaction().commit();
		System.out.println("Compra guardada!");
		System.out.println(compra);
		em.close();
	}
}
