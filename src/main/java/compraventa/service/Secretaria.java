package compraventa.service;

public class Secretaria {
	private String nombre;
	
	public Secretaria(String nombre) {
		this.nombre = nombre;
	}
	
	public void imprimir(String hoja) {
		Impresora imp = new Impresora();
		imp.imprimir(hoja);
	}
	
	
	class Impresora {
		public void imprimir(String hoja) {
			System.out.println("Secretaria(" + nombre + ")");
			System.out.println(hoja);
		}
	}
	
	
	public static void main(String[] args) {
		Secretaria s = new Secretaria("Sec. DGIC");
		s.imprimir("hola mundo");
	}
}
