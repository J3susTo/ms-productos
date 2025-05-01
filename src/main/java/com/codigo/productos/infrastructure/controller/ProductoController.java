package com.codigo.productos.infrastructure.controller;

import com.codigo.productos.application.port.input.ProductoUseCase;
import com.codigo.productos.domain.model.Producto;
import com.codigo.productos.infrastructure.client.AuthClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoUseCase productoUseCase;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoUseCase.crearProducto(producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoUseCase.listarProductos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoUseCase.editarProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    // NUEVO ENDPOINT
    @GetMapping("/verificar/{id}")
    public ResponseEntity<Producto> verificarProducto(@PathVariable Long id) {
        Producto producto = productoUseCase.buscarProductoPorId(id); // Debes implementar esto en tu UseCase
        return ResponseEntity.ok(producto);
    }

}