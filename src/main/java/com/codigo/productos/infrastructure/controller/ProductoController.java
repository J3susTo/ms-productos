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
    private final AuthClient authClient;

    // Helper: Validar token
    private void validarToken(HttpServletRequest request, String... rolesPermitidos) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Token no proporcionado");
        }

        var user = authClient.validateToken(token);

        boolean autorizado = false;
        for (String rol : rolesPermitidos) {
            if (rol.equalsIgnoreCase(user.getRol())) {
                autorizado = true;
                break;
            }
        }

        if (!autorizado) {
            throw new RuntimeException("No autorizado para esta operación");
        }
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, HttpServletRequest request) {
        validarToken(request, "ADMIN", "SUPERADMIN");
        return ResponseEntity.ok(productoUseCase.crearProducto(producto));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(HttpServletRequest request) {
        validarToken(request, "ADMIN", "SUPERADMIN");
        return ResponseEntity.ok(productoUseCase.listarProductos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto, HttpServletRequest request) {
        validarToken(request, "ADMIN", "SUPERADMIN");
        return ResponseEntity.ok(productoUseCase.editarProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id, HttpServletRequest request) {
        validarToken(request, "ADMIN", "SUPERADMIN");
        productoUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
