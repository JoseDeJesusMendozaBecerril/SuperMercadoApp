package net.pepe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Productos") //como esta exactamente en la base de datos para que se mapeen atributo a campo de la bd
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String proveedor;
	private String imagen="no-image.png";
	private int destacado;
	private double precio;
	
	

	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getDestacado() {
		return destacado;
	}
	

	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}


	@OneToOne //configurar como relacion 1 a 1 clase modelo producto y clase modelo departamento
	@JoinColumn(name="idDepartamento") //especificar con que campo se hace la relacion
	private Departamento departamento;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getProveedor() {
		return proveedor;
	}


	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", proveedor=" + proveedor + ", imagen=" + imagen
				+ ", destacado=" + destacado + ", precio=" + precio + ", departamento=" + departamento + "]";
	}


	


	
	
	
	
	
	
	
}
