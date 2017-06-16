package compraventa.exception;

import javax.ejb.ApplicationException;

/**
 * Excepción general de la capa de lógica de negocios.
 * 
 * @author jorge
 */
@ApplicationException(rollback = true)
public class BusinessException extends Exception {

	private static final long serialVersionUID = -8386454717399047543L;

	private final static String ERROR_MSG = "[BE] Error de lógica de negocios";

	public BusinessException() {
		super(ERROR_MSG);
	}

	public BusinessException(Throwable cause) {
		super(ERROR_MSG, cause);
	}

	public BusinessException(String msg) {
		super(msg);
	}
}
