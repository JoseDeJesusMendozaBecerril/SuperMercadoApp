package net.pepe.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.pepe.model.Producto;
import net.pepe.repository.ProductosRepository;
import net.pepe.service.IProductosService;

@Service
@Primary
public class ProductosServiceJpa implements IProductosService  {

	@Autowired
	private ProductosRepository productosRepo;
	
	@Override
	public List<Producto> buscarTodas() {
		
		return productosRepo.findAll();
	}

	@Override
	public Producto buscarPorId(int idProducto) {
		 Optional<Producto> optional = productosRepo.findById(idProducto);
		 if(optional.isPresent()) {
			 return optional.get();
		 }
		return null;
	}

	@Override
	public void guardar(Producto producto) {
		productosRepo.save(producto);

	}

	@Override
	public void eliminar(int idProducto) {
		productosRepo.deleteById(idProducto);
		
	}

}
