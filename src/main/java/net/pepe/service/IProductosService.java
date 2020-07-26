package net.pepe.service;

import java.util.List;

import net.pepe.model.Producto;

public interface IProductosService {
	
	List <Producto> buscarTodas();
	Producto buscarPorId(int idProducto);
	void guardar(Producto producto);
	void eliminar(int idProducto);
}
