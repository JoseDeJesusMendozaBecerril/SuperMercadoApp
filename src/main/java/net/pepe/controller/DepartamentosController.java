package net.pepe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.pepe.model.Departamento;
import net.pepe.service.IDepartamentosService;
import net.pepe.service.IProductosService;

@Controller 
@RequestMapping(value="/departamentos")
public class DepartamentosController {
	
		@Autowired
		@Qualifier("departamentosServiceJpa")
		private IDepartamentosService serviceDepartamentos;
	
		
		@Autowired
		@Qualifier("productosServiceJpa")
		private IProductosService serviceProductos;
		
		// @GetMapping("/index")
		@RequestMapping(value="/index", method=RequestMethod.GET) 
		public String mostrarIndex(Model model){
			List<Departamento> lista = serviceDepartamentos.buscarTodas();
			model.addAttribute("listaDepartamentos",lista);
			return "departamentos/listDepartamentos"; 
		} 
		
		// @GetMapping("/create") 
		@RequestMapping(value="/create", method=RequestMethod.GET) 
		public String crear(Departamento departamento, Model model) { 
			
			return "departamentos/formDepartamento"; 
		} 
		
		// @PostMapping("/save")  
		@RequestMapping(value="/save", method=RequestMethod.POST)  //AQUI RECIBO LOS VALORES DE LOS CAMPOS DE HTML CON ESOS NOMBRES 
		public String guardar(Departamento departamento , BindingResult result, RedirectAttributes attributes){ //ESTE METODO RECIBE POR POST INFO DE UN FORMULARIO
			if(result.hasErrors()){
				for(ObjectError error : result.getAllErrors()) {
					System.out.println("Ocurrio un error " + error.getDefaultMessage());
				}
				return "departamentos/formDepartamento"; //Si ocurre un error 
			}
			
			serviceDepartamentos.guardar(departamento);
			attributes.addFlashAttribute("msg","Departamento Guardado Con Exito"); // FLASH ATTRIBUTE
			
			System.out.println(departamento);
			
			return "redirect:/departamentos/index"; 
		}
		
		@GetMapping("/edit/{id}")
		public String editar(@PathVariable("id") int idDepartamento, Model model) {
			Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
			model.addAttribute("departamento",departamento);
			
			return "departamentos/formDepartamento";
		}
		
		@GetMapping("/delete/{id}")
		public String eliminar(@PathVariable("id") int idDepartamento, RedirectAttributes attributes) {		
			// Eliminamos departamento.
			serviceDepartamentos.eliminar(idDepartamento);			
			attributes.addFlashAttribute("msg", "El departamento fue eliminada!.");
			return "redirect:/departamentos/index";
		}
		
		@GetMapping("/view/{id}") //Este recibe cuando mandamos id al dar click en verdetalles
		public String verDetalle(@PathVariable("id") int idDepartamento , Model model){
			
			Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
			model.addAttribute("departamento",departamento);
			return "detalle";
		}
		
		
		@ModelAttribute
		public void setGenericos(Model model) {
			model.addAttribute("productos",serviceProductos.buscarTodas());
		}
		
		
	}

