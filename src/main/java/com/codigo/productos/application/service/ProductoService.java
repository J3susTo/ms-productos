package com.codigo.productos.application.service;

import com.codigo.productos.application.port.input.ProductoUseCase;
import com.codigo.productos.application.port.output.ProductoRepositoryPort;
import com.codigo.productos.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements ProductoUseCase {

    private final ProductoRepositoryPort productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto editarProducto(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setNombre(producto.getNombre());
                    existingProduct.setPrecio(producto.getPrecio());
                    existingProduct.setCategoria(producto.getCategoria());
                    return productoRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.delete(id);
    }

    @Override
    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
}
