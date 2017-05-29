package compraventa.registrador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Esta clase muestra cómo podemos leer el contenido de un archivo de texto.
 * 
 * @author Jorge Ramirez
 */
public class EjemploLecturaArchivo {

	public static void main(String[] args) {
		File file;
		BufferedReader br;

		try {
			file = new File("src/main/resources/operaciones.csv");
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();

			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
