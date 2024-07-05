package com.gcba.test.services;


import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAll();
    ProductoDTO getById(Long id);
    ProductoDTO add(Producto producto);
    ProductoDTO update(Producto producto);
    void delete(Long id);
    List<Producto> productosSinDescripcion();
    List<Producto> getProductsSoldLastDay();
}
