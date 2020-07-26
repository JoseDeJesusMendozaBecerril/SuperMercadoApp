package net.pepe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pepe.model.Departamento;

//PRIMER REPOSITORIO DE SPRING DATA JPA
/*Un repositorio es una interfaz que extiende CRUD REPOSITORY
 *  esta interfaz ya tiene implementadas operaciones CRUD  auna Base de datos
 *  NOS VA AYUDAR A ADMINISTRAR LOS TIPOS CATEGORIA
 */


//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface DepartamentosRepository extends JpaRepository<Departamento, Integer> {
	
	
	
	
}
