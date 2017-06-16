package compraventa.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CantidadNegativaException extends BusinessException {

	private static final long serialVersionUID = 4437007293137899096L;

	public CantidadNegativaException() {
		super("La cantidad no puede ser negativa");
	}
}
