package compraventa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		Producto banana = new Producto("Banana", 10);
		System.out.println("Existencia inicial: " + banana.getExistencia());
		Venta venta = VentasBusiness.vender(banana, 5);
		System.out.println(venta);
		System.out.println("Existencia actual: " + banana.getExistencia());
	
		
		Compra compra = CompraBusiness.comprar(banana, 20);
		System.out.println(compra);
		System.out.println("Existencia actual: " + banana.getExistencia());
		
		
		
		Cajero cajero = new Cajero();
		cajero.setNombre("Jorge");
		cajero.setIdCaja(1);
		
		System.out.println(cajero);
		
		
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre("Jorge");
		vendedor.setProducto(banana);
		System.out.println(vendedor);
		
		Nombrable p = new Vendedor();
		p.setNombre("pepito");
		
		
		Comparable<String> cmp = new Comparable<String>();
		System.out.println(cmp.iguales("hola", "hola"));
		
		Comparable<Integer> cmpInt = new Comparable<Integer>();
		System.out.println(cmpInt.iguales(1, 2));
		
		Comparable<Persona> comPersona = new Comparable<Persona>();
		System.out.println(comPersona.iguales(cajero, vendedor));
		
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(cajero);
		personas.add(vendedor);
		
		for(Persona e: personas) {
			System.out.println(e);
		}
		
		Map<String, Persona> hash = new HashMap<>();
		hash.put("cajero", cajero);
	}
}
