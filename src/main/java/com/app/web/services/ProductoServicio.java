package com.app.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entities.Producto;
import com.app.web.repository.ProductoRepository;

@Service
public class ProductoServicio implements InterfaceProductoServicio {

	@Autowired
	private ProductoRepository repositorio;
	
	@Override
	public List<Producto> listar() {
		
		return repositorio.findAll();
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return repositorio.save(producto);
	}

	@Override
	public Producto obtenerProductoId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Producto updateProducto(Producto producto) {
		return repositorio.save(producto) ;
	}

	@Override
	public void delete(Long id) {
		repositorio.deleteById(id);
		
	}
	
}
