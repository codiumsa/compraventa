package compraventa;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import compraventa.dao.ProductosDAO;
import compraventa.model.Producto;

/**
 * Define los test unitarios para {@link ProductosDAO}.
 * 
 * 
 * 
 * 
 * // correr pruebas y empaquetar
 *  mvn clean package -Pwildfly-remote
 * // empaquetar y no correr pruebas
 * mvn clean package -DskipTests
 * 
 * @author jorge
 */
@RunWith(Arquillian.class)
public class ProductosDAOTest {

	@Inject
	private ProductosDAO dao;

	/**
	 * Se encarga de crear el archivo test.war que contiene
	 * 
	 * @return
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				// agregamos el proyecto completo
				.addPackages(true, "compraventa")
				// agregamos recursos necesarios
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("arquillian-ds.xml").addAsWebInfResource("META-INF/beans.xml")
				.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml", "wildfly-remote")
						.importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile());
	}

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