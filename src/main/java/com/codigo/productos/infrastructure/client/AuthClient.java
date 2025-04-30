package com.codigo.productos.infrastructure.client;

import com.codigo.productos.infrastructure.client.dto.UsuarioAuthDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    @Value("${auth.validate.url}")
    private String authValidateUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioAuthDTO validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<UsuarioAuthDTO> response = restTemplate.exchange(
                authValidateUrl,
                HttpMethod.GET,
                requestEntity,
                UsuarioAuthDTO.class
        );

        return response.getBody();
    }
}
