package compraventa.exceptions;

public class StockMinimoException extends Exception {
	
	public StockMinimoException() {
		super("Cantidad insuficiente");
	}
}
