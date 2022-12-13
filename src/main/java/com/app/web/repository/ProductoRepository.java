package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.app.web.entities.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
