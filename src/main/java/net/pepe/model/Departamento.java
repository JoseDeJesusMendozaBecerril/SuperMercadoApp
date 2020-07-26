package net.pepe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Mapear al modelo de la base de datos

@Entity
@Table(name="Departamentos") //nombre debe de coincidir mayusculas y minusculas con nombre de la tabla de la BD
public class Departamento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Manera de decir que el atributo será autoincrementable
	private Integer id; //Mapear la llave primaria al atributo ID
	private String nombre;
	private String jefe;
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", jefe=" + jefe
				+ ", descripcion=" + descripcion + "]";
	}

	

}
