package net.pepe.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.pepe.model.Departamento;

@Service
//primary
public class DepartamentosServiceImpl implements IDepartamentosService {

	LinkedList<Departamento> listaDepartamentos = null;

	
	
	public DepartamentosServiceImpl() {
		listaDepartamentos = new LinkedList<>();
		
		try {
			Departamento dpto1 = new Departamento();
			dpto1.setId(1);
			dpto1.setNombre("Carnes");
			dpto1.setDescripcion("Departamento dedicado a la venta de carnes y embutidos");
			
			Departamento dpto2 = new Departamento();
			dpto2.setId(2);
			dpto2.setNombre("Lacteos");
			dpto2.setDescripcion("Departamento dedicado a la venta de lacteos");
			
			Departamento dpto3 = new Departamento();
			dpto3.setId(3);
			dpto3.setNombre("Sopas");
			dpto3.setDescripcion("Departamento dedicado a la venta de sopas");
			
			Departamento dpto4 = new Departamento();
			dpto4.setId(4);
			dpto4.setNombre("Plasticos");
			dpto4.setDescripcion("Departamento dedicado a la venta de bolsas");
			
			
			
			listaDepartamentos.add(dpto1);
			listaDepartamentos.add(dpto2);
			listaDepartamentos.add(dpto3);
			listaDepartamentos.add(dpto4);
			
			
			
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	
	
	@Override
	public void guardar(Departamento departamento) {
		this.listaDepartamentos.add(departamento);

	}

	@Override
	public List<Departamento> buscarTodas() {
		return this.listaDepartamentos;
	}

	@Override
	public Departamento buscarPorId(Integer idDepartamento) {
		for(Departamento departamento: listaDepartamentos) {
			if(departamento.getId() == idDepartamento) {
				return departamento;
			}
		}
		
		return null;
	}


	@Override
	public void eliminar(Integer idDepartamento) {
		// TODO Auto-generated method stub
		
	}

}
