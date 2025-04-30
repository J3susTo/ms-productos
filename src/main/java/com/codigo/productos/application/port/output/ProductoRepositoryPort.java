package com.codigo.productos.application.port.output;

import com.codigo.productos.domain.model.Producto;

import java.util.List;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
    Producto findById(Long id);
    Producto update(Long id, Producto producto); // <- Agregado
    void delete(Long id);
}
