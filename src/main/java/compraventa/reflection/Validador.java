package compraventa.reflection;

import java.lang.reflect.Field;

import compraventa.Compra;
import compraventa.Venta;

/**
 * Validador simple usando reflection y annotations.
 * 
 * @author jorge
 */
public class Validador {

	/**
	 * Permite verificar si el objeto dado como parámetro es válido.
	 * 
	 * @param src
	 * @return
	 */
	public boolean isValid(Object src) {

		for (Field field : src.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Positivo p = field.getAnnotation(Positivo.class);

			try {
				if (p != null) {
					Integer value = (Integer) field.get(src);
					
					// cómo determinar si el field es de tipo Integer.
//					if(field.getType().isAssignableFrom(Integer.class)) {
//						Integer value = (Integer) field.get(src);
//					}

					if (value < 0) {
						return false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Validador v = new Validador();
		Compra c = new Compra();
		c.setCantidad(-10);
		System.out.println("Es una compra válida: " + v.isValid(c));
		
		Venta venta = new Venta();
		venta.setCantidad(-100);
		System.out.println("Es una venta válida: " + v.isValid(venta));
	}
}
