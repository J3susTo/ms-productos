package com.codigo.productos.infrastructure.repository;

import com.codigo.productos.domain.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private List<Producto> productos = new ArrayList<>();

    @Override
    public Producto save(Producto producto) {
        productos.add(producto);
        return producto;
    }

    @Override
    public List<Producto> findAll() {
        return productos;
    }

    @Override
    public Producto findById(Long id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        productos.removeIf(producto -> producto.getId().equals(id));
    }
}
