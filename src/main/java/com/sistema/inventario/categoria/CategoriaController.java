package com.sistema.inventario.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	//Listar 
	@GetMapping("/categorias")
	public String listarCategorias(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		modelo.addAttribute("listaCategorias", listaCategorias);
		return "categorias";
	}
	
	//Mostrar un formulario
	@GetMapping("/categorias/nuevo")
	public String mostrarFormularioDeNuevaCategoria(Model modelo) {
		modelo.addAttribute("categoria", new Categoria());
		return "categoria_formulario";
	}
	
	@PostMapping("/categorias/guardar")
	public String guardarCategoria(Categoria categoria ) {
		categoriaRepository.save(categoria);
		return "redirect:/categorias";
	}
	
	@GetMapping("/categorias/editar/{id}")
	public String mostrarFormularioModificarCategoria(@PathVariable("id") Integer id, Model modelo) {
		Categoria categoria = categoriaRepository.findById(id).get();
		
		modelo.addAttribute("categoria", categoria);
		
		return "categoria_formulario";		
	}
	
	
	@GetMapping("/categorias/eliminar/{id}")
	public String eliminarCategoria(@PathVariable("id") Integer id, Model modelo) {
		categoriaRepository.deleteById(id);
		
		return "redirect:/categorias";
	}
	
	
}
