package com.codigo.productos.application.port.input;

import com.codigo.productos.domain.model.Producto;

import java.util.List;

public interface ProductoUseCase {
    Producto crearProducto(Producto producto);
    List<Producto> listarProductos();
    Producto editarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
    Producto buscarProductoPorId(Long id);

}
