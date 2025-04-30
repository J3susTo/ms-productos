package com.codigo.productos.infrastructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAuthDTO {
    private Long id;
    private String email;
    private String rol;
    private String username;
}
