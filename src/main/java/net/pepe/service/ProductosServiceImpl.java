package net.pepe.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.pepe.model.Producto;

@Service //para que la escanee y la pueda utilizar en otra inyectar 
public class ProductosServiceImpl implements IProductosService {
	
	//esto cambiara se sacara de una tabla de una base de datos
	private List<Producto> lista = null;	
	
	public ProductosServiceImpl() {
		
		try {
			//Creamos Oferta de Trabajo 1
			Producto producto1 = new Producto();
			producto1.setId(1);
			producto1.setNombre("Jugo Boing ");
			producto1.setProveedor("Boing");
			producto1.setImagen("imagen1.png");
			
			
			Producto producto2 = new Producto();
			producto2.setId(2);
			producto2.setNombre("Mayonesa KcKormic ");
			producto2.setProveedor("Mckormick");
			producto2.setImagen("imagen2.png");
			
			Producto producto3 = new Producto();
			producto3.setId(3);
			producto3.setNombre("Lata atun dolores");
			producto3.setProveedor("Atun Dolores");
			producto3.setImagen("imagen3.png");
			
			Producto producto4 = new Producto();
			producto4.setId(4);
			producto4.setNombre("Lata chiles chipotles");
			producto4.setProveedor("Chipotles rajas");
			producto4.setImagen("imagen4.png");
			
			lista.add(producto1);
			lista.add(producto2);
			lista.add(producto3);
			lista.add(producto4);
			
		}catch(Exception ex) {
			System.out.println("Error: "+ex.getMessage());
		}
	}
	
	
	@Override
	public List<Producto> buscarTodas() {
		return this.lista;
	}

	
	
	@Override
	public Producto buscarPorId(int idProducto) {
		for(Producto v : lista ) {
			if(v.getId() == idProducto) {
				return v;
			}
		}
		return null; //sino se encontro
		
	}

	@Override
	public void guardar(Producto producto) {
		this.lista.add(producto);
	}

	@Override
	public void eliminar(int idProducto) {		
	}
	
	
}
