package com.gcba.test.repositories;

import com.gcba.test.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository  extends JpaRepository<Venta, Long> {
    List<Venta> findByFechaBetween(Date start, Date end);
}
