package com.app.web.services;

import java.util.List;

import com.app.web.entities.Producto;

public interface InterfaceProductoServicio {
	public List<Producto>listar();
	public Producto guardarProducto(Producto producto);
	public Producto obtenerProductoId(Long id);
	public Producto updateProducto(Producto producto);
	public void delete(Long id);
}
