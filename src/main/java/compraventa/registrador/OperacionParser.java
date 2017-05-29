package compraventa.registrador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que nos permite parsear un archivo de operaciones de compra/venta.
 * 
 * @author jorge
 */
public class OperacionParser {

	/**
	 * Método que permite leer y procesar un archivo de compras/ventas.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<Operacion> parse(String path) throws IOException {
		List<Operacion> operaciones = new ArrayList<Operacion>();
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();

		while (line != null) {
			String[] parts = line.split(";");
			Operacion o = new Operacion();
			o.setFecha(parts[0]);
			o.setTipo(parts[1]);
			o.setCodigoProducto(parts[2]);
			o.setNombreProducto(parts[3]);
			o.setCantidad(Integer.parseInt(parts[4]));
			operaciones.add(o);
			line = br.readLine();
		}
		br.close();
		return operaciones;
	}

	public static class Operacion {
		private String fecha;
		private String tipo;
		private String codigoProducto;
		private String nombreProducto;
		private Integer cantidad;

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getCodigoProducto() {
			return codigoProducto;
		}

		public void setCodigoProducto(String codigoProducto) {
			this.codigoProducto = codigoProducto;
		}

		public String getNombreProducto() {
			return nombreProducto;
		}

		public void setNombreProducto(String nombreProducto) {
			this.nombreProducto = nombreProducto;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
	}
}
