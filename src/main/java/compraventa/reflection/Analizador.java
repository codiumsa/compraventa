package compraventa.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import compraventa.Compra;
import compraventa.Producto;

public class Analizador {

	/**
	 * Analizador de clases, simple.
	 * 
	 * @param klazz
	 */
	public void analizar(Class<?> klazz) {
		String nombre = klazz.getName();
		System.out.println("\nNombre de la clase: " + nombre);
		Field[] fields = klazz.getDeclaredFields();
		System.out.println("Campos");
		
		for (Field field : fields) {
			String nombreCampo = field.getName();
			String nombreTipo = field.getType().getSimpleName();
			System.out.println("\tnombre del campo: " + nombreCampo + ", tipo: " + nombreTipo);
			Annotation[] anotaciones = field.getAnnotations();
			System.out.println("\t\tAnotaciones del campo: ");
			
			for(Annotation annotation: anotaciones) {
				System.out.println("\t\t\t nombre: " + annotation.annotationType().getSimpleName());
			}
		}
		Method[] methods = klazz.getDeclaredMethods();
		System.out.println("Métodos");
		
		for(Method method: methods) {
			String nombreMetodo = method.getName();
			int cantidadParametros = method.getParameterCount();
			System.out.println("\tnombre del método: " + nombreMetodo + ", cantidad de parámetros: " + cantidadParametros);
		}
	}

	public static void main(String[] args) {
		Analizador a = new Analizador();
		a.analizar(Producto.class);
		a.analizar(Compra.class);
		
	}
}
