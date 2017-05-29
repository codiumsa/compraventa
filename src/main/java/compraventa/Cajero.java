package compraventa;

/**
 * Esta clase representa a un cajero dentro del sistema.
 * 
 * 
 * @author Jorge Ramirez
 * @since 0.1.0
 */
public class Cajero extends Persona {

	private Integer idCaja;

	public Integer getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Integer idCaja) {
		this.idCaja = idCaja;
	}
	
	public String toString() {
		return super.toString() + " y soy cajer";
	}
}
