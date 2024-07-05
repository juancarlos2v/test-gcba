package com.gcba.test.repositories;

import com.gcba.test.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p Where p.nombre IS NOT NULL AND p.descripcion IS NULL")
    List<Producto> findByNombreNotNullAndDescripcionNull();
}
