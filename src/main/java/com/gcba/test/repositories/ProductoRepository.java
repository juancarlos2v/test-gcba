package com.gcba.test.repositories;

import com.gcba.test.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {
}
