package com.codigo.productos.infrastructure.client.dto;

import lombok.Data;

@Data
public class UsuarioAuthDTO {
    private String nombre;
    private String email;
    private String rol;
}
