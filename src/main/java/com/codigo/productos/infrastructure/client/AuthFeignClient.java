package com.codigo.productos.infrastructure.client;

import com.codigo.productos.infrastructure.client.dto.UsuarioAuthDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-client", url = "${auth.validate.url}")
public interface AuthFeignClient {
    @GetMapping("/auth/validate")
    UsuarioAuthDTO validateToken(@RequestHeader("Authorization") String token);
}
