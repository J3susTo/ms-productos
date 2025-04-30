package com.codigo.productos.infrastructure.adapter;

import com.codigo.productos.application.port.output.ProductoRepositoryPort;
import com.codigo.productos.domain.model.Producto;
import com.codigo.productos.infrastructure.entity.ProductoEntity;
import com.codigo.productos.infrastructure.mapper.ProductoMapper;
import com.codigo.productos.infrastructure.repository.ProductoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoRepositoryJpa repositoryJpa;

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = ProductoMapper.toEntity(producto);
        return ProductoMapper.toModel(repositoryJpa.save(entity));
    }

    @Override
    public List<Producto> findAll() {
        return repositoryJpa.findAll().stream()
                .map(ProductoMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Producto update(Long id, Producto producto) {
        ProductoEntity entity = repositoryJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        entity.setNombre(producto.getNombre());
        entity.setPrecio(producto.getPrecio());
        entity.setCategoria(producto.getCategoria());
        return ProductoMapper.toModel(repositoryJpa.save(entity));
    }
    @Override
    public Producto findById(Long id) {
        ProductoEntity entity = repositoryJpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return ProductoMapper.toModel(entity);
    }

    @Override
    public void delete(Long id) {
        repositoryJpa.deleteById(id);
    }
}
