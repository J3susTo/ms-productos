package com.codigo.productos.infrastructure.adapter;

import com.codigo.productos.domain.model.Producto;
import com.codigo.productos.infrastructure.entity.ProductoEntity;
import com.codigo.productos.infrastructure.mapper.ProductoMapper;
import com.codigo.productos.infrastructure.repository.ProductoRepositoryJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoRepositoryAdapterTest {

    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @InjectMocks
    private ProductoRepositoryAdapter productoRepositoryAdapter;

    private Producto producto;
    private ProductoEntity productoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = Producto.builder()
                .id(1L)
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        productoEntity = ProductoEntity.builder()
                .id(1L)
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
    }

    @Test
    void testSave() {
        when(productoRepositoryJpa.save(any(ProductoEntity.class))).thenReturn(productoEntity);
        Producto result = productoRepositoryAdapter.save(producto);
        assertNotNull(result);
        assertEquals(producto.getNombre(), result.getNombre());
    }

    @Test
    void testFindAll() {
        when(productoRepositoryJpa.findAll()).thenReturn(Arrays.asList(productoEntity));
        List<Producto> productos = productoRepositoryAdapter.findAll();
        assertEquals(1, productos.size());
    }

    @Test
    void testFindById() {
        when(productoRepositoryJpa.findById(1L)).thenReturn(Optional.of(productoEntity));
        Optional<Producto> result = productoRepositoryAdapter.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(producto.getNombre(), result.get().getNombre());
    }

    @Test
    void testDelete() {
        doNothing().when(productoRepositoryJpa).deleteById(1L);
        productoRepositoryAdapter.delete(1L);
        verify(productoRepositoryJpa, times(1)).deleteById(1L);
    }
}
