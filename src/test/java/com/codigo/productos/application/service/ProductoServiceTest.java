package com.codigo.productos.application.service;

import com.codigo.productos.application.port.output.ProductoRepositoryPort;
import com.codigo.productos.domain.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepositoryPort repository;

    @InjectMocks
    private ProductoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProducto_debeRetornarProductoGuardado() {
        Producto producto = Producto.builder().id(1L).nombre("Croqueta").precio(12.5).categoria("Perro").build();
        when(repository.save(producto)).thenReturn(producto);

        Producto resultado = service.crearProducto(producto);

        assertEquals(producto, resultado);
        verify(repository).save(producto);
    }

    @Test
    void listarProductos_debeRetornarListaDeProductos() {
        List<Producto> productos = List.of(
                Producto.builder()
                        .id(1L)
                        .nombre("Comida")
                        .precio(10.0)
                        .categoria("Gato")
                        .build()
        );

        when(repository.findAll()).thenReturn(productos);

        List<Producto> resultado = service.listarProductos();

        assertEquals(productos, resultado);
    }

    @Test
    void editarProducto_productoExiste_debeRetornarActualizado() {
        Producto existente = Producto.builder().id(1L).nombre("Antiguo").precio(10.0).categoria("Perro").build();
        Producto nuevo = Producto.builder().nombre("Nuevo").precio(20.0).categoria("Gato").build();

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        Producto resultado = service.editarProducto(1L, nuevo);

        assertEquals("Nuevo", resultado.getNombre());
        assertEquals(20.0, resultado.getPrecio());
        assertEquals("Gato", resultado.getCategoria());
    }

    @Test
    void editarProducto_productoNoExiste_debeLanzarExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                service.editarProducto(99L, Producto.builder().build())
        );
    }

    @Test
    void eliminarProducto_debeLlamarDelete() {
        service.eliminarProducto(1L);
        verify(repository).delete(1L);
    }

    @Test
    void buscarProductoPorId_existe_debeRetornarProducto() {
        Producto producto = Producto.builder().id(1L).nombre("Test").precio(5.0).categoria("Ave").build();
        when(repository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = service.buscarProductoPorId(1L);

        assertEquals(producto, resultado);
    }

    @Test
    void buscarProductoPorId_noExiste_debeLanzarExcepcion() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarProductoPorId(2L));
    }
}
