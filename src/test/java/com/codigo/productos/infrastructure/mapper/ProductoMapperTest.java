package com.codigo.productos.infrastructure.mapper;

import com.codigo.productos.domain.model.Producto;
import com.codigo.productos.infrastructure.entity.ProductoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoMapperTest {

    @Test
    void testToEntity() {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Alimento para gato")
                .precio(12.5)
                .categoria("Gato")
                .build();

        ProductoEntity entity = ProductoMapper.toEntity(producto);

        assertNotNull(entity);
        assertEquals(producto.getId(), entity.getId());
        assertEquals(producto.getNombre(), entity.getNombre());
        assertEquals(producto.getPrecio(), entity.getPrecio());
        assertEquals(producto.getCategoria(), entity.getCategoria());
    }

    @Test
    void testToModel() {
        ProductoEntity entity = ProductoEntity.builder()
                .id(2L)
                .nombre("Alimento para perro")
                .precio(15.0)
                .categoria("Perro")
                .build();

        Producto producto = ProductoMapper.toModel(entity);

        assertNotNull(producto);
        assertEquals(entity.getId(), producto.getId());
        assertEquals(entity.getNombre(), producto.getNombre());
        assertEquals(entity.getPrecio(), producto.getPrecio());
        assertEquals(entity.getCategoria(), producto.getCategoria());
    }
}
