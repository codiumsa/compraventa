package compraventa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad que representa a la tabla compra_detalle.
 * 
 * @author jorge
 */
@Entity
@Table(name = "compra_detalle")
public class CompraDetalle implements Serializable {

	private static final long serialVersionUID = -4111366531053619037L;

	@Id
	@SequenceGenerator(name = "compra_detalle_id_seq", sequenceName = "compra_detalle_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "compra_detalle_id_seq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
