package com.codigo.productos.infrastructure.client;

import com.codigo.productos.infrastructure.client.dto.UsuarioAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AuthClient {

    private static final Logger logger = LoggerFactory.getLogger(AuthClient.class);

    @Value("${auth.validate.url}")
    private String authValidateUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioAuthDTO validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            logger.info("Enviando solicitud de validación al servicio de autenticación: {}", authValidateUrl);
            ResponseEntity<UsuarioAuthDTO> response = restTemplate.exchange(
                    authValidateUrl,
                    HttpMethod.GET,
                    requestEntity,
                    UsuarioAuthDTO.class
            );
            logger.info("Respuesta de validación recibida con código: {}", response.getStatusCode());
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            logger.error("Error del cliente AuthClient: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new RuntimeException("Token inválido o expirado");
        } catch (Exception ex) {
            logger.error("Error inesperado al validar token: {}", ex.getMessage());
            throw new RuntimeException("Error al validar el token");
        }
    }
}