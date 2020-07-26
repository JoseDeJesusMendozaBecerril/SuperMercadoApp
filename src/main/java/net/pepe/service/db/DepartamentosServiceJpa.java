package net.pepe.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.pepe.model.Departamento;
import net.pepe.repository.DepartamentosRepository;
import net.pepe.service.IDepartamentosService;

@Service
//@Primary  //Por si hay otra que implemente ICategoriasService como a la hora de inyectar el servicio se hace con ICategoriasService no sabe que implementacion tomar //Entonces si le decimos primary tomará primero esta
public class DepartamentosServiceJpa implements IDepartamentosService {
	
	@Autowired
	private DepartamentosRepository departamentosRepo;
	
	
	@Override
	public void guardar(Departamento departamento) {
		departamentosRepo.save(departamento);
	}

	@Override
	public List<Departamento> buscarTodas() {
		
		return departamentosRepo.findAll();
	}
	@Override
	public Departamento buscarPorId(Integer idDepartamento) {
		Optional<Departamento> optional =  departamentosRepo.findById(idDepartamento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idDepartamento) {
		departamentosRepo.deleteById(idDepartamento);
		
	}

}
