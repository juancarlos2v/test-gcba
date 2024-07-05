package com.gcba.test.services;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.dto.ResponseDetalle;
import com.gcba.test.entities.DetalleVenta;
import com.gcba.test.entities.Producto;
import com.gcba.test.repositories.DetalleVentaRepository;
import com.gcba.test.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final DetalleVentaServiceImpl detalleVentaService;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoDTO getById(Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        return new ProductoDTO(p);
    }

    @Override
    public ProductoDTO add(Producto producto) {
        productoRepository.save(producto);
        return new ProductoDTO(producto);
    }

    @Override
    @Transactional
    public ProductoDTO update(Producto producto) {
        Producto p = productoRepository.findById(producto.getId()).orElse(null);
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
        p.setCantidad(producto.getCantidad());
        productoRepository.save(p);
        return new ProductoDTO(p);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> productosSinDescripcion() {
        return productoRepository.findByNombreNotNullAndDescripcionNull();
    }

    @Override
    public List<Producto> getProductsSoldLastDay() {
        List<Producto> productos=getAll();
        Set<Long> idProducts=detalleVentaService.getIdProductSoldLastDay();
        return productos.stream()
                .filter(producto->idProducts.contains(producto.getId()))
                .collect(Collectors.toList());
    }
}
