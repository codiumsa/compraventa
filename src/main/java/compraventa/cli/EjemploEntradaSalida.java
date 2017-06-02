package compraventa.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que muestra ejemplos de cómo leer de la entrada estándar y
 * cómo escribir a un archivo.
 * 
 * Java soporta tres Flujos Estándar (Standard Streams). La Entrada Estándar, disponible
 * utilizando {@link System.in}; Salida Estándar, disponible utilizando {@link System.out};
 * y Salida de Error Estándar, disponible utilizando {@link System.err}.
 * 
 * Cómo compilar este ejemplo desde la terminal.
 * 
 * javac compraventa/cli/EjemploEntradaSalida.java
 * 
 * Ejecutar con
 * 
 * java compraventa.cli.EjemploEntradaSalida
 * 
 * @author jorge
 */
public class EjemploEntradaSalida {

	public static void main(String[] args) {
		// leer de entrada estándar
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("Ingresa tu nombre: ");
		while(true) {
			
			try {
				String line = br.readLine();
				System.out.printf("Hola, %s\n", line);
				System.out.print("Ingresa tu nombre: ");				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
