package com.carritodecompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carritodecompras.model.ProductoCarrito;
import com.carritodecompras.model.ProductoStock;
import com.carritodecompras.model.Usuario;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


/**
 *Repositorio para manejar persistencia de productos en carrito en la base de datos.
 * 
 *
 *
 */
public interface ProductoCarritoRepository extends JpaRepository<ProductoCarrito, Long>{
	
	
	List<ProductoCarrito> findByCliente(Usuario cliente);

}
