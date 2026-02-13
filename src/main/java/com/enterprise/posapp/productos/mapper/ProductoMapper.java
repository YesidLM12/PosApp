package com.enterprise.posapp.productos.mapper;

import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.model.entity.Productos;

public class ProductoMapper {
    public static ProductoResponse toResponse(Productos productos){
        return new ProductoResponse(
                productos.getNombre(),
                productos.getPrecio(),
                productos.getCategoria().getNombre(),
                productos.isActivo()
        );
    }
}
