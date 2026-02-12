package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.productos.model.entity.Categorias;


public interface CategoriaRepository {
    void save(Categorias categoria);

    Categorias findByNombre(String nombre);


}
