package com.gcba.test.repositories;

import com.gcba.test.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository  extends JpaRepository<DetalleVenta, Long> {
}
