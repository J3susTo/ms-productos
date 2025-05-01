package com.codigo.productos.application.port.output;

import com.codigo.productos.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);
    List<Producto> findAll();
    Producto update(Long id, Producto producto); // <- Agregado
    void delete(Long id);
    Optional<Producto> findById(Long id);
}
