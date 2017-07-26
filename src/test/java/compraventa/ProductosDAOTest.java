package compraventa;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import compraventa.dao.ProductosDAO;
import compraventa.model.Producto;

/**
 * Define los test unitarios para {@link ProductosDAO}.
 * 
 * <code>
 * // correr pruebas y empaquetar 
 * mvn clean package -Pwildfly-remote 
 * // empaquetar y no correr pruebas 
 * mvn clean package -DskipTests
 * </code>
 * 
 * @author jorge
 */
@RunWith(Arquillian.class)
public class ProductosDAOTest extends BaseTest {

	@Inject
	private ProductosDAO dao;

	@Test
	@InSequence(1)
	public void createTest() throws Exception {
		Producto producto = new Producto();
		producto.setCodigo("BAN01");
		producto.setNombre("Banana de oro");
		producto.setExistencia(100);
		producto.setPrecio(2000L);
		dao.persist(producto);
		Assert.assertNotNull("El producto creado debe tener un ID", producto.getId());
	}

	@Test
	@InSequence(2)
	public void testAll() throws Exception {
		List<Producto> productos = dao.all();
		Assert.assertFalse("Deben existir productos en la base de datos", productos.isEmpty());
	}

}
