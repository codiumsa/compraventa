package compraventa;

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
