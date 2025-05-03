package com.codigo.productos.infrastructure.repository;

import com.codigo.productos.infrastructure.entity.ProductoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryJpaTest {

    @Autowired
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Test
    void testSaveAndFindById() {
        ProductoEntity producto = ProductoEntity.builder()
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        ProductoEntity savedProducto = productoRepositoryJpa.save(producto);
        Optional<ProductoEntity> foundProducto = productoRepositoryJpa.findById(savedProducto.getId());
        assertTrue(foundProducto.isPresent());
        assertEquals("Comida", foundProducto.get().getNombre());
    }

    @Test
    void testFindAll() {
        ProductoEntity producto1 = ProductoEntity.builder()
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        ProductoEntity producto2 = ProductoEntity.builder()
                .nombre("Juguete")
                .precio(5.0)
                .categoria("Perro")
                .build();
        productoRepositoryJpa.save(producto1);
        productoRepositoryJpa.save(producto2);
        List<ProductoEntity> productos = productoRepositoryJpa.findAll();
        assertEquals(2, productos.size());
    }

    @Test
    void testDeleteById() {
        ProductoEntity producto = ProductoEntity.builder()
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        ProductoEntity savedProducto = productoRepositoryJpa.save(producto);
        productoRepositoryJpa.deleteById(savedProducto.getId());
        Optional<ProductoEntity> foundProducto = productoRepositoryJpa.findById(savedProducto.getId());
        assertFalse(foundProducto.isPresent());
    }
}
