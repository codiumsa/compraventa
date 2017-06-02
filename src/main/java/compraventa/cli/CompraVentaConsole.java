package compraventa.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import compraventa.Compra;
import compraventa.CompraBusiness;
import compraventa.Producto;
import compraventa.Venta;
import compraventa.VentasBusiness;

/**
 * Clase que encapsula la lógica de compra/venta en su versión CLI.
 * 
 * @author jorge
 */
public class CompraVentaConsole {

	private File splash;
	private String option;
	private BufferedReader in;
	private Map<String, Producto> productos;
	private List<Compra> compras;
	private List<Venta> ventas;

	public CompraVentaConsole() {
		splash = new File("src/main/resources/splash.txt");
		in = new BufferedReader(new InputStreamReader(System.in));
		productos = new HashMap<>();
		compras = new ArrayList<>();
		ventas = new ArrayList<>();
	}

	/**
	 * Punto de inicio del sistema compra venta.
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		boolean run = true;

		while (run) {
			showSplash();
			System.out.print("Ingrese opción: ");
			option = in.readLine();

			switch (option) {
			case "1":
				crearProducto();
				break;
			case "2":
				listarProductos();
				break;
			case "3":
				comprarProducto();
				break;
			case "4":
				venderProducto();
				break;
			case "5":
				reporteVenta();
				break;
			case "6":
				exportarVentasCSV();
				break;
			case "7":
				run = false;
				System.out.println("Cerrando aplicación....");
				System.out.println("OK");
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}
		}
	}

	/**
	 * Método utilizado para mostrar el menú de opciones.
	 */
	public void showSplash() {
		try {
			// Mostramos el menú de opciones
			BufferedReader br = new BufferedReader(new FileReader(splash));
			String line = br.readLine();
			do {
				System.out.println(line);
				line = br.readLine();
			} while (line != null);
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que encapsula la lógica de creación de producto.
	 * 
	 * @throws Exception
	 */
	private void crearProducto() throws Exception {
		String buffer;
		System.out.println("Alta de Productos");
		System.out.print("Código: ");
		buffer = in.readLine();

		if (productos.containsKey(buffer)) {
			System.out.println("Existe un producto para el código: " + buffer);
			return;
		}
		Producto p = new Producto();
		p.setCodigo(buffer);

		System.out.print("Nombre: ");
		buffer = in.readLine();
		p.setNombre(buffer);

		System.out.print("Existencia inicial: ");
		buffer = in.readLine();
		boolean existenciaOK = true;

		try {
			p.setExistencia(Integer.parseInt(buffer));
			existenciaOK = p.getExistencia() > 0;
		} catch (NumberFormatException e) {
			existenciaOK = false;
		}

		if (!existenciaOK) {
			System.out.println("La existencia debe ser un valor numérico positivo.");
			return;
		}

		System.out.print("Precio: ");
		buffer = in.readLine();
		boolean precioOK = true;

		try {
			p.setPrecio(Long.parseLong(buffer));
			precioOK = p.getPrecio() > 0;
		} catch (NumberFormatException e) {
			precioOK = false;
		}

		if (!precioOK) {
			System.out.println("El precio debe ser un valor numérico positivo.");
			return;
		}
		productos.put(p.getCodigo(), p);
		System.out.println("Producto creado!");
	}

	/**
	 * Método que encapsula la lógica de listado de productos.
	 */
	private void listarProductos() {
		if (productos.values().isEmpty()) {
			System.out.println("No se registraron productos.");
			return;
		}
		
		for (Producto p : productos.values()) {
			System.out.println(p);
		}
	}

	/**
	 * Método que encapsula la lógica de comprar productos.
	 */
	private void comprarProducto() throws Exception {
		System.out.print("Ingrese código del Producto: ");
		String buffer = in.readLine();

		Producto p = productos.get(buffer);

		if (p == null) {
			System.out.println("No existe un producto con el código: " + buffer);
			return;
		}

		System.out.print("Cantidad de " + p.getNombre() + " a comprar: ");
		buffer = in.readLine();
		boolean cantidadOK = true;
		int cantidad = 0;

		try {
			cantidad = Integer.parseInt(buffer);
			cantidadOK = cantidad > 0;
		} catch (NumberFormatException e) {
			cantidadOK = false;
		}

		if (!cantidadOK) {
			System.out.println("La cantidad debe ser un número positivo.");
			return;
		}
		Compra compra = CompraBusiness.comprar(p, cantidad);
		compras.add(compra);
		System.out.println("Operación exitosa!");
	}

	/**
	 * Método que encapsula la lógica de venta de productos.
	 * 
	 * @throws Exception
	 */
	private void venderProducto() throws Exception {
		System.out.print("Ingrese código del Producto: ");
		String buffer = in.readLine();

		Producto p = productos.get(buffer);

		if (p == null) {
			System.out.println("No existe un producto con el código: " + buffer);
			return;
		}

		System.out.print("Cantidad de " + p.getNombre() + " a vender: ");
		buffer = in.readLine();
		boolean cantidadOK = true;
		int cantidad = 0;

		try {
			cantidad = Integer.parseInt(buffer);
			cantidadOK = cantidad > 0;
		} catch (NumberFormatException e) {
			cantidadOK = false;
		}

		if (!cantidadOK) {
			System.out.println("La cantidad debe ser un número positivo.");
			return;
		}
		Venta venta = VentasBusiness.vender(p, cantidad);
		ventas.add(venta);
		System.out.println("Operación exitosa!");
	}

	/**
	 * Implementa un reporte de ventas simple.
	 */
	private void reporteVenta() {
		if (ventas.isEmpty()) {
			System.out.println("No se registraron ventas.");
			return;
		}
		long total = 0;

		for (Venta v : ventas) {
			System.out.println(v);
			total += v.getCantidad() * v.getProducto().getPrecio();
		}
		System.out.println("Total vendido hasta la fecha: " + total + " Gs.");
	}

	/**
	 * Lógica para exportar las ventas a un archivo CSV.
	 */
	private void exportarVentasCSV() {
		File csv = new File("src/main/resources/ventas.csv");
		try {
			csv.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(csv));
			String buffer;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			for (Venta v : ventas) {
				buffer = v.getProducto().getCodigo() + ";" + v.getProducto().getNombre() + ";";
				buffer += v.getCantidad() + ";" + (v.getCantidad() * v.getProducto().getPrecio()) + ";";
				buffer += sdf.format(v.getFecha()) + "\n";
				br.write(buffer);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error al generar exportar las ventas. Intente de nuevo.");
			return;
		}
		System.out.println("Archivo src/main/resources/ventas.csv generado!");
	}
}
