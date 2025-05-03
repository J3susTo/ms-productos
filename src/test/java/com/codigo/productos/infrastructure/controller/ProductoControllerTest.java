package com.codigo.productos.infrastructure.controller;

import com.codigo.productos.application.port.input.ProductoUseCase;
import com.codigo.productos.domain.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoUseCase productoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        Mockito.when(productoUseCase.crearProducto(any(Producto.class))).thenReturn(producto);
        mockMvc.perform(post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Comida"));
    }

    @Test
    void testListarProductos() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        Mockito.when(productoUseCase.listarProductos()).thenReturn(Arrays.asList(producto));
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Comida"));
    }

    @Test
    void testBuscarProductoPorId() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Comida")
                .precio(10.0)
                .categoria("Gato")
                .build();
        Mockito.when(productoUseCase.buscarProductoPorId(1L)).thenReturn(producto);
        mockMvc.perform(get("/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Comida"));
    }

    @Test
    void testEditarProducto() throws Exception {
        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Comida Actualizada")
                .precio(12.0)
                .categoria("Gato")
                .build();
        Mockito.when(productoUseCase.editarProducto(eq(1L), any(Producto.class))).thenReturn(producto);
        mockMvc.perform(put("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Comida Actualizada"));
    }

    @Test
    void testEliminarProducto() throws Exception {
        Mockito.doNothing().when(productoUseCase).eliminarProducto(1L);
        mockMvc.perform(delete("/productos/1"))
                .andExpect(status().isOk());
    }
}
