package compraventa.cli;

/**
 * Clase Main para probar {@link CompraVentaConsole}.
 *  *
 *
 * Para correr el programa desde la terminal, ubicarse
 * en el directorio raíz del proyecto y ejecutar:
 * 
 * $ mvn exec:java -Dexec.mainClass="compraventa.cli.Main"
 * 
 * @author jorge
 */
public class Main {
	
	public static void main(String[] args) {
		CompraVentaConsole cvc = new CompraVentaConsole();
		try {
			cvc.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
