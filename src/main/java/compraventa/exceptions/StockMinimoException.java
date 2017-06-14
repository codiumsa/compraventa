package compraventa.exceptions;

public class StockMinimoException extends Exception {
	
	private static final long serialVersionUID = 7723605959927057620L;

	public StockMinimoException() {
		super("Cantidad insuficiente");
	}
}
