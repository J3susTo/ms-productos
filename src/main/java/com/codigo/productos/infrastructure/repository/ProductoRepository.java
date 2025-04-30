package com.codigo.productos.infrastructure.repository;

import com.codigo.productos.domain.model.Producto;

import java.util.List;

public interface ProductoRepository {
    Producto save(Producto producto);
    List<Producto> findAll();
    Producto findById(Long id);
    void delete(Long id);
}
