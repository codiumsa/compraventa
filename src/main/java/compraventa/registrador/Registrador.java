package compraventa.registrador;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import compraventa.CompraBusiness;
import compraventa.Producto;
import compraventa.VentasBusiness;
import compraventa.exceptions.CantidadNegativaException;
import compraventa.exceptions.StockMinimoException;
import compraventa.registrador.OperacionParser.Operacion;

/**
 * Clase que permite registrar en modo "batch" operaciones de compra/venta.
 * 
 * @author jorge
 */
public class Registrador {

	/**
	 * Este metodo permite registrar las operaciones de compra/venta almacenadas en el archivo referenciado
	 * por el path dado como parámetro.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws CantidadNegativaException
	 * @throws StockMinimoException
	 */
	public Collection<Producto> registrar(String path) throws IOException, CantidadNegativaException, StockMinimoException {
		Map<String, Producto> productosMap = new HashMap<>();
		OperacionParser parser = new OperacionParser();
		List<Operacion> operaciones = parser.parse(path);

		for (Operacion o : operaciones) {

			if (!productosMap.containsKey(o.getCodigoProducto())) {
				Producto p = new Producto(o.getCodigoProducto(), o.getNombreProducto());
				productosMap.put(o.getCodigoProducto(), p);
			}
			Producto p = productosMap.get(o.getCodigoProducto());

			if (o.getTipo().equals("C")) {
				CompraBusiness.comprar(p, o.getCantidad());
			} else if (o.getTipo().equals("V")) {
				VentasBusiness.vender(p, o.getCantidad());
			}
		}
		return productosMap.values();
	}
	
	public static void main(String[] args) {
		String fechaStr = "2017-03-23";
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		try {
			System.out.println("Fecha sin parsear: " + fechaStr);
			Date fecha = sdf.parse(fechaStr);
			System.out.println("Fecha parseada: " + fecha);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		
		Registrador registrador = new Registrador();
		
		try {
			Collection<Producto> productos = registrador.registrar("src/main/resources/operaciones.csv");
			
			for(Producto p: productos) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
