package com.carritodecompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carritodecompras.model.Categoria;


/**
 *Repositorio para manejar persistencia de las categorias en la base de datos.
 * 
 *
 *
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
