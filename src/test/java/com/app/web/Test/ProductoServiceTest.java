package com.app.web.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.web.entities.Producto;
import com.app.web.services.ProductoServicio;


@SpringBootTest
public class ProductoServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ProductoServiceTest.class);
	
	@Autowired
	private ProductoServicio servicio;
	
	@Test
	public void testFindProductobyID() {
		long ID = 1;
		String NOMBRE = "producto 1";
		Producto p = null;
		try {
			p = servicio.obtenerProductoId(ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		logger.info("" + p);
		assertThat(p.getNombre(), is(NOMBRE));
	}
	
	@Test
	public void testCreateProducto() {
		String NOMBRE_PRODUCTO = "Producto Test";
		String IMG_URL = "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg";
		float PRECIO_PRODUCTO = 5.99f;
		String DESCRIPCION_PRODUCTO = "descripcion del producto test";
		Producto p = new Producto(NOMBRE_PRODUCTO, DESCRIPCION_PRODUCTO, PRECIO_PRODUCTO, IMG_URL);
		Producto pcreate = servicio.guardarProducto(p);
		logger.info("PRODUCTO CREADO" + pcreate);
		
		assertThat(p.getId(), notNullValue());
		assertThat(p.getNombre(), is(NOMBRE_PRODUCTO));
		assertThat(p.getDescripcion(), is(DESCRIPCION_PRODUCTO));
		assertThat(p.getImg(), is(IMG_URL));
		assertThat(p.getPrecio(), is(PRECIO_PRODUCTO));
	}
	@Test
	public void testUpdatePet() {
		long create_id = -1;
		String PRODUCTO_NAME = "Producto Test 2";
		String PRODUCTO_DESCRIPCION = "Descripcion del producto Test 2";
		float PRODUCTO_PRECIO = 10.99f;
		String URL_IMG = "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg";
		
		String UP_PRODUCTO_NAME = "Producto Test 2 Update";
		String UP_PRODUCTO_DESCRIPCION = "Descripcion del producto Test 2 Update";
		float UP_PRODUCTO_PRECIO = 5.99f;
		String UP_URL_IMG = "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/02/comohaceralitas.jpg";
		
		Producto p = new Producto(PRODUCTO_NAME,PRODUCTO_DESCRIPCION,PRODUCTO_PRECIO,URL_IMG);
		logger.info(">" + p);
		Producto pcreate = servicio.guardarProducto(p);
		logger.info(">>" + pcreate);
		
		create_id=pcreate.getId();
		
		pcreate.setNombre(UP_PRODUCTO_NAME);
		pcreate.setDescripcion(UP_PRODUCTO_DESCRIPCION);
		pcreate.setPrecio(UP_PRODUCTO_PRECIO);
		pcreate.setImg(UP_URL_IMG);
		
		Producto updateProducto = servicio.updateProducto(pcreate);
		logger.info(">>>>" + updateProducto);
		assertThat(create_id, notNullValue());
		assertThat(updateProducto.getId(), is(create_id));
		assertThat(updateProducto.getNombre(), is(UP_PRODUCTO_NAME));
		assertThat(updateProducto.getDescripcion(), is(UP_PRODUCTO_DESCRIPCION));
		assertThat(updateProducto.getPrecio(), is(UP_PRODUCTO_PRECIO));
		assertThat(updateProducto.getImg(), is(UP_URL_IMG));
		
	}
	@Test
	public void testDeleteProducto() {
		String PRODUCTO_NAME = "Producto test delete";
		String PRODUCTO_DESCRIPCION = "Descripcion producto test delete";
		float PRODUCTO_PRECIO = 12.99f;
		String URL_IMG = "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/02/comohaceralitas.jpg";
		Producto p = new Producto(PRODUCTO_NAME,PRODUCTO_DESCRIPCION,PRODUCTO_PRECIO,URL_IMG);
		p = servicio.guardarProducto(p);
		logger.info("" + p);
		try {
			servicio.delete(p.getId());
			System.out.println("Producto Eliminado");
		} catch (Exception e) {
			fail(e.getMessage());
		}
			
		try {
			servicio.obtenerProductoId(p.getId());
			fail("Pet id = " + p.getId() + " has not delete");
		} catch (Exception e) {
		} 	
		
	}
}
