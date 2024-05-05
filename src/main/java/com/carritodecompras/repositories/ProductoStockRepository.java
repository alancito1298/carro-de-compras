package com.carritodecompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carritodecompras.model.Categoria;
import com.carritodecompras.model.ProductoStock;

import java.util.List;



public interface ProductoStockRepository extends JpaRepository<ProductoStock, Long>{

	List<ProductoStock> findByCategoria(Categoria categoria);
	
	List<ProductoStock> findByPrecioBetween(Double min,Double max);
	
}
