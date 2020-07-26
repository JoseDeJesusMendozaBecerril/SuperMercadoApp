package net.pepe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.pepe.model.Producto;

public interface ProductosRepository extends JpaRepository<Producto, Integer> {
	
	//Metodo usado para query method
	//List<Producto> findByEstatus(String estatus); //Aqui se respetan las letras como usare query method uso la clave findBy
												 // Tambien Estatus porque mi nombre en modelo es estatus 
	
	// List <Producto> findByDestacadoAndEstatusOrderByIdDesc(int destacado , String estatus ); //Uso findBy y And y Estatus OrderBy ordenados Desc descendente
	
	// List <Producto> findBySalarioBetweenOrderBySalarioDesc(double s1,double s2);  
	
	// List <Producto> findByEstatusIn(String[] estatus);
	
	
}
