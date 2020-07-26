package net.pepe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.pepe.model.Producto;
import net.pepe.service.IProductosService;

@Controller
public class HomeController {
	
	@Autowired //Inyecto el servicio
	private IProductosService serviceProductos;
	
	
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();
		model.addAttribute("listaProductos",lista);
		
		return "tabla"; //nombre del html
	}
	
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Producto producto = new Producto();
		producto.setNombre("Shampoo head shoulder");
		producto.setProveedor("Head Showlder");
		model.addAttribute("producto",producto); //podemos manipular desde el HTML
		return "detalle"; //nombre del html
	}
	
	
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		/* 
		 	model.addAttribute("mensaje","Bienvenidos a Empleos App"); //Agregar datos al modelo
			model.addAttribute("fecha",new Date());
	
			String nombre= "Auxiliar de contabilidad";
			Date fechaPub = new Date();
			double salario = 30000.0;
			boolean vigente=true;
			
			model.addAttribute("nombre",nombre); //Podemos manipular desde el html añade al model
			model.addAttribute("fecha",fechaPub);
			model.addAttribute("salario",salario);
			model.addAttribute("vigente",vigente);
		
	*/
		List<Producto> lista = serviceProductos.buscarTodas();
		model.addAttribute("listaProductos",lista);
		
		return "home"; //nombre de la vista 
	}
	
	@GetMapping("/listado") //al solicitar esta url 
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Shampoo");
		lista.add("Gelatina de Fresa");
		lista.add("Papel Higienico");
		lista.add("Jugo Jumex");
		
		model.addAttribute("productos",lista);
		
		return "listado"; //vista de html que devuelve
	}
	
		
		
		
	
	
	
	
}
