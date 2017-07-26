package compraventa;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Clase simple de pruebas. Se creó utilizando el wizard de eclipse.
 * 
 * @author jorge
 */
public class SimpleTestCase {

	@Test
	public void testSonNumerosIguales() {
		Integer num = Integer.valueOf(800);
		assertSame(num, num);
	}

}