package compraventa.cli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que muestra cómo crear y escribir en un archivo.
 * 
 * @author jorge
 */
public class EjemploEscribirArchivo {

	public static void main(String[] args) {
		File nuevo = new File("src/main/resources/nuevo.txt");
		try {
			nuevo.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(nuevo));
			bw.write("hola esto es una línea");
			System.out.println("Archivo nuevo.txt generado!");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
