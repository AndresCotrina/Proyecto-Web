package com.app.web.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.entities.Producto;
import com.app.web.services.InterfaceProductoServicio;

@Controller
@RequestMapping
public class AdminController {
	@Autowired
	private InterfaceProductoServicio servicio;
	
	@GetMapping("/admin")
	public String method() {
	    return "redirect:" + "/admin/productos";
	}
	// Listar
	@GetMapping("/admin/productos")
	public String listarAdmin(Model mdl) {
		mdl.addAttribute("admin", servicio.listar());
		return "admin";
	}
	// Agregar
	@GetMapping("/admin/agregar")
	public String crearProducto(Model mdl) {
		mdl.addAttribute("producto", new Producto());
		return "agregar";
	}
	@PostMapping("/admin/producto/save")
	public String guardarProducto(@ModelAttribute("producto") Producto p) {
		servicio.guardarProducto(p);
		return "redirect:/admin/productos";

	}
	// Editar
	@GetMapping("/admin/editar/{id}")
	public String EditarProducto(@PathVariable Long id, Model mdl) {
		mdl.addAttribute("producto", servicio.obtenerProductoId(id));
		return "edit";
	}
	@PostMapping("/admin/producto/{id}")
	public String update(@PathVariable Long id, @ModelAttribute("producto") Producto producto, Model mdl) {
		Producto productoExiste = servicio.obtenerProductoId(id);
		productoExiste.setId(id);
		productoExiste.setNombre(producto.getNombre());
		productoExiste.setDescripcion(producto.getDescripcion());
		productoExiste.setPrecio(producto.getPrecio());
		productoExiste.setImg(producto.getImg());

		servicio.updateProducto(productoExiste);
		return "redirect:/admin/productos";
	}
	//Eliminar
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable Long id) {
		servicio.delete(id);
		return "redirect:/admin/productos";
	}

}
