package com.codigo.productos.domain.ports.in;

import com.codigo.productos.infrastructure.client.AuthFeignClient;
import com.codigo.productos.infrastructure.client.dto.UsuarioAuthDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final AuthFeignClient authFeignClient;

    public ProductoServiceImpl(AuthFeignClient authFeignClient) {
        this.authFeignClient = authFeignClient;
    }

    @Override
    public void algunaOperacion(String token) {
        try {
            UsuarioAuthDTO usuario = authFeignClient.validateToken("Bearer " + token);
            // Lógica con el usuario autenticado
            System.out.println("Usuario autenticado: " + usuario.getUsername());
        } catch (Exception e) {
            // Manejo básico de errores
            System.err.println("Error al validar token: " + e.getMessage());
            throw new RuntimeException("Token inválido o servicio de autenticación no disponible");
        }
    }
}
