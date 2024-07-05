package com.gcba.test.services;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
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
        try {
            return productoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO getById(Long id) {
        Producto p = productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return new ProductoDTO(p);
    }

    @Override
    @Transactional
    public ProductoDTO add(Producto producto) {
        try {
            if (producto.getId() != null) throw new IllegalArgumentException("ID no de debe agregarse manualmente");
            productoRepository.save(producto);
            return new ProductoDTO(producto);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error al agregar producto: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo procesar la solictud");
        }
    }

    @Override
    @Transactional
    public ProductoDTO update(Producto producto) {
        Producto p = productoRepository.findById(producto.getId()).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
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
        try {
            productoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Producto con ID: " + id + ", no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("No se pudo procesar la solictud: " + e.getMessage());
        }
    }


}
