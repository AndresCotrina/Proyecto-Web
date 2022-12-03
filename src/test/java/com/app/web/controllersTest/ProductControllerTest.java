package com.app.web.controllersTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.web.Test.ProductoServiceTest;
import com.app.web.entities.Producto;
import com.app.web.exception.ProductoNotFoundException;
import com.app.web.repository.ProductoRepository;
import com.app.web.services.InterfaceProductoServicio;
import com.app.web.services.ProductoServicio;
@RestController
public class ProductControllerTest {
	private static final Logger logger 
	= LoggerFactory.getLogger(ProductControllerTest.class);

	@Autowired
	private ProductoServicio servicio;
	
	// @JsonIgnore
	@GetMapping("/getProductos")
	public Iterable<Producto> getProducts(){
		return servicio.listar();
	}
	
	
	//Crear Producto
	@PostMapping("/createProducto")
	@ResponseStatus(HttpStatus.CREATED)
	Producto create(@RequestBody Producto p) {
		Producto producto = new Producto();
		producto.setNombre(p.getNombre());
		producto.setImg(p.getImg());
		producto.setPrecio(p.getPrecio());
		producto.setDescripcion(p.getDescripcion());
		
		return servicio.guardarProducto(p);
		
	}
	
	//Obtener por ID
	@GetMapping("/getProducto/{id}")
	ResponseEntity<Producto> findOne(@PathVariable Long id) throws ProductoNotFoundException{
		return new ResponseEntity<Producto>(servicio.obtenerProductoId(id),HttpStatus.OK);
	}
	
	
	//Update 
	Producto saveOrUpdate(@RequestBody Producto newProducto,@PathVariable Long id) {
		Producto p = null;
		logger.info("saveOrUpdate: " + newProducto.toString());
		try {
			p = servicio.obtenerProductoId(id);
			p.setNombre(newProducto.getNombre());
			p.setImg(newProducto.getImg());
			p.setPrecio(newProducto.getPrecio());
			p.setDescripcion(newProducto.getDescripcion());
			
		} catch (Exception e) {
			p = servicio.guardarProducto(newProducto);
		}
		return p;
	}
	
	//eliminar por ID
	@DeleteMapping("/deleteProducto/{id}")
	ResponseEntity<String> delete(@PathVariable Long id) throws ProductoNotFoundException{
		servicio.delete(id);
		return new ResponseEntity<>("" + id, HttpStatus.OK);
	}
	
}
