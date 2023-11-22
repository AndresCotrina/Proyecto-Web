package com.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.web.services.InterfaceProductoServicio;

@Controller
@RequestMapping
public class ProductoController {
	@Autowired
	private InterfaceProductoServicio servicio;
	@GetMapping("")
	public String listar(Model mdl) {
		mdl.addAttribute("productos", servicio.listar());
		return "productos";
	}
	@GetMapping("/productos")
	public String listarStore(Model mdl) {
		mdl.addAttribute("store", servicio.listar());
		return "store";
	}

}
