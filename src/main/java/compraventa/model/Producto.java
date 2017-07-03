package compraventa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import compraventa.service.View;
import compraventa.validation.CaseMode;
import compraventa.validation.CheckCase;

@Entity
@Table(name = "producto")
@NamedQueries({ @NamedQuery(name = "Producto.count", query = "SELECT count(p) from Producto p") })
public class Producto implements Serializable {
	private static final long serialVersionUID = 368804720253655075L;

	@Id
	@SequenceGenerator(name = "producto_id_seq", sequenceName = "producto_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "producto_id_seq")
	private Long id;

	@Column(name = "nombre")
	@NotNull
	private String nombre;

	@Column(name = "existencia")
	@NotNull
	private Integer existencia = 0;

	@Column(name = "codigo")
	@CheckCase(CaseMode.UPPER)
	@NotNull
	private String codigo;

	@Column(name = "precio")
	@NotNull
	private Long precio;

	public Producto() {
	}

	public Producto(String nombre, Integer existencia) {
		this.nombre = nombre;
		this.existencia = existencia;
	}

	public Producto(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String toString() {
		return "[ID: " + id + "] " + nombre + " (" + codigo + ") " + existencia + " unidades en stock. Precio: "
				+ precio;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
