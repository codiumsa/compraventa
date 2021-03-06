package compraventa.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import compraventa.serialization.DateFormat;
import compraventa.serialization.JSonDateDeserializer;
import compraventa.serialization.JsonDateSerializer;
import compraventa.service.View;

/**
 * Esta es la entidad que represneta a las compras.
 * 
 * @author jorge
 */

@Entity
@Table(name = "compra")
@NamedQueries({ @NamedQuery(name = "Compra.count", query = "SELECT count(c) from Compra c") })
public class Compra implements Serializable {

	// null reference;
	public static final Compra NULL = null;

	private static final long serialVersionUID = 6398338866521001688L;

	@Id
	@SequenceGenerator(name = "compra_id_seq", sequenceName = "compra_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "compra_id_seq")
	@JsonView(View.Public.class)
	private Long id;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
	@JsonView(View.ConDetalle.class)
	private List<CompraDetalle> detalles = new ArrayList<>();

	@NotNull
	@JsonView(View.Public.class)
	@DateFormat("dd/MM/yyyy")
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JSonDateDeserializer.class)
	private Date fecha;

	@JsonView(View.Public.class)
	@Transient
	private Long total;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CompraDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<CompraDetalle> detalles) {
		this.detalles = detalles;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String msg = "Compra. ID: " + id + ", fecha: " + sdf.format(fecha) + "\nDetalles: ";

		for (CompraDetalle detalle : detalles) {
			msg += "\n Producto: " + detalle.getProducto().getNombre() + ", cantidad: " + detalle.getCantidad()
					+ ", Precio: " + detalle.getProducto().getPrecio();
		}
		return msg;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
