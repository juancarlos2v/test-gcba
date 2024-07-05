package com.gcba.test.services;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
import com.gcba.test.exception.RequestException;
import com.gcba.test.repositories.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO getById(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RequestException("P-404", "Producto no encontrado"));
        return new ProductoDTO(p);
    }

    @Override
    @Transactional
    public ProductoDTO add(Producto producto) {
        if (producto.getId() != null) throw new RequestException("p-500", "ID no de debe agregarse manualmente");
        productoRepository.save(producto);
        return new ProductoDTO(producto);
    }

    @Override
    @Transactional
    public ProductoDTO update(Producto producto) {
        Producto p = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RequestException("P-404", "Producto no encontrado"));
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
        p.setCantidad(producto.getCantidad());
        productoRepository.save(p);
        return new ProductoDTO(p);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.findById(id)
                .orElseThrow(() -> new RequestException("P-404", "Producto no encontrado"));
        productoRepository.deleteById(id);
    }
}
