package com.gcba.test.controllers;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
import com.gcba.test.exception.RequestException;
import com.gcba.test.services.ProductoService;
import com.gcba.test.services.ProductoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProducts() {
        return productoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProduct(@PathVariable Long id) {
            ProductoDTO product = productoService.getById(id);
          //  if(product == null)throw new RequestException("P-404", "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody Producto producto) {
        ProductoDTO product = productoService.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProduct(@RequestBody Producto producto) {
            ProductoDTO p = productoService.update(producto);
            return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
