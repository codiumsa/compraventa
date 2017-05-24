package compraventa;

public class Main {
	
	public static void main(String[] args) {
		Producto banana = new Producto("Banana", 10);
		System.out.println("Existencia inicial: " + banana.getExistencia());
		Venta venta = VentasBusiness.vender(banana, 5);
		System.out.println(venta);
		System.out.println("Existencia actual: " + banana.getExistencia());
	}
}
