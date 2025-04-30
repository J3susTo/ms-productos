package com.codigo.productos.infrastructure.mapper;

import com.codigo.productos.domain.model.Producto;
import com.codigo.productos.infrastructure.entity.ProductoEntity;

public class ProductoMapper {

    public static ProductoEntity toEntity(Producto producto) {
        return ProductoEntity.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .categoria(producto.getCategoria())
                .build();
    }

    public static Producto toModel(ProductoEntity entity) {
        return Producto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .categoria(entity.getCategoria())
                .build();
    }
}
