package compraventa;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * Clase base para las pruebas unitarias.
 * 
 * @author jorge
 */
public class BaseTest {

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
}
