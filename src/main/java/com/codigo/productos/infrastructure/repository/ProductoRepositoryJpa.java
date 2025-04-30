package com.codigo.productos.infrastructure.repository;

import com.codigo.productos.infrastructure.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositoryJpa extends JpaRepository<ProductoEntity, Long> {
}
