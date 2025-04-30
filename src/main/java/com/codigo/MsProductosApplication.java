package com.codigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.codigo.productos.infrastructure.client")
public class MsProductosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsProductosApplication.class, args);
    }
}

