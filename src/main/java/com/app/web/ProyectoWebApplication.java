package com.app.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.web.entities.Producto;
import com.app.web.repository.ProductoRepository;


@SpringBootApplication
public class ProyectoWebApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoWebApplication.class, args);
	}

	@Autowired
	private ProductoRepository repositorio;
	@Override
	public void run(String... args) throws Exception {
		/*
		 * 		
		Producto p1 = new Producto("producto 1", "descripcio 1",12.99f, "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg");	
		repositorio.save(p1);
		Producto p2 = new Producto("producto 2", "descripcio 2",8.99f, "https://es.cravingsjournal.com/wp-content/uploads/2019/11/alitas-bbq-1.jpg");
		repositorio.save(p2);
		 * */
		
		
	}

}
