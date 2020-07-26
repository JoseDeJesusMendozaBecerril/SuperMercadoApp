package net.pepe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.pepe.model.Producto;
import net.pepe.service.IDepartamentosService;
import net.pepe.service.IProductosService;
import net.pepe.util.Utileria;

@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	@Value("${supermercadoapp.ruta.imagenes}") //Inyeccion de propiedades del archivo application.propiertys
	private String ruta;
	
	@Autowired //Inyecto el servicio
	private IProductosService serviceProductos;
	
	@Autowired //Inyecto el servicio
	@Qualifier("departamentosServiceJpa")
	private IDepartamentosService serviceDepartamentos;
	
	
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();	// TO DO 1. Obtener todas las vacantes (recuperarlas de la clase de servicio)
		model.addAttribute("listaProductos",lista); 			    //TO DO 2. Agregar al modelo el listado de vacantes
		return "productos/listProductos"; 						//TO DO 3. Renderizar las vacantes en la vista (integrar los archivos template-empleos/listVacantes.html)
																// TO DO 4. Agregar al menu una opcion llamada "vacantes" configurando la URL "vacante/index"
	}
	
	@GetMapping("/listProductosPrecio")
	public String mostrarProductosPrecio(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();
		model.addAttribute("listaProductos",lista);
		return "productos/listProductosprecio";
	}
	
	
	@GetMapping("/listProductodepartamento")
	public String mostrarProductodepartamento(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();
		model.addAttribute("listaProductos",lista);
		return "productos/listProductodepartamento";
	}
	
	@GetMapping("/editDepartamento/{id}")
	public String editarDepartamento(@PathVariable("id") int idProducto, Model model) {
		Producto producto = serviceProductos.buscarPorId(idProducto);
		model.addAttribute("producto",producto);
		
		return "productos/formProductodepartamento";
	}
	
	
	
	@GetMapping("/create")
	public String crear(Producto producto, Model model) {
		
		return "productos/formProducto";
	}
	
	
	@PostMapping("/save")
	public String guardar(Producto producto,BindingResult result ,RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart){ //ESTOS PARAMETROS VIENEN DEL FORMULARIO POR POST DATABINDING LOS JALA SOLOS Y COMPARA CON EL MODELO NAA MAMEES 
		if(result.hasErrors()){
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error " + error.getDefaultMessage());
			}
			return "productos/formProducto"; //Si ocurre un error 
		}
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				producto.setImagen(nombreImagen);
			}
		}
		
		
		serviceProductos.guardar(producto);
		attributes.addFlashAttribute("msg","Registro Guardado con Exito"); // FLASH ATTRIBUTE
		//model.addAttribute("msg","Registro Guardado"); //Este atributo solo estara disponible para cuando se hace la peticion post (/save)
													   //Y como abajo hago un redirect es decir otra peticion http de tipo GET cuando sucede 
														// este atributo ya no estara disponible por lo que desaparece la solucion a esto es -> Flash Attributes
														//Flash permiten que se usen los atributos en varias peticiones
		System.out.println(producto); //QUE CHINGOOON
		return "redirect:/productos/index"; //indirectamente hago peticion http a la url vacantes/index que esta el metodo de arriba donde si se carga la lista sino lo hago 
		
		
	}

	
	
	/* 
	@PostMapping("/save")
	public String guardar( //ESTOS PARAMETROS VIENEN DEL FORMULARIO POR POST
			@RequestParam("nombre") String nombre, 
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles
    ){
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario: " + salario);
		System.out.println("Detalles: " + detalles);
		
		return "vacantes/listVacantes";
	}
	*/
	

	@GetMapping("/delete/{clave_producto}")
	public String eliminar(@PathVariable("clave_producto")  int idProducto, RedirectAttributes attributes) { //buscara parametro con ese id y lo asocia a idVacante
		System.out.println("Borrando producto con id : "+ idProducto);
		serviceProductos.eliminar(idProducto);
		attributes.addFlashAttribute("msg","La producto fue eliminada");
		
		return "redirect:/productos/index";
	}
	
	@GetMapping("/view/{id}") //Este recibe cuando mandamos id al dar click en verdetalles
	public String verDetalle(@PathVariable("id") int idProducto , Model model){
		
		Producto producto = serviceProductos.buscarPorId(idProducto);
		model.addAttribute("producto",producto);
		return "detalle";
	}
	

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idProducto, Model model) {
		Producto producto = serviceProductos.buscarPorId(idProducto);
		model.addAttribute("producto",producto);
		
		return "productos/formProducto";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("departamentos",serviceDepartamentos.buscarTodas());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
