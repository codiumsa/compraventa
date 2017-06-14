package compraventa.exceptions;

public class CantidadNegativaException extends Exception {
	
	private static final long serialVersionUID = 4437007293137899096L;

	public CantidadNegativaException() {
		super("La cantidad no puede ser negativa");
	}
}
