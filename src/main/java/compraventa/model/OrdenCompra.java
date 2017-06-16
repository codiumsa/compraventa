package compraventa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orden_compra")
public class OrdenCompra implements Serializable {

	private static final long serialVersionUID = -1506206107313391143L;

	@Id
	@SequenceGenerator(name = "orden_compra_id_seq", sequenceName = "orden_compra_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "orden_compra_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	private Integer cantidad;

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
	
	

}
