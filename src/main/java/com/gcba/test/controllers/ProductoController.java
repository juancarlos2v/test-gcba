package com.gcba.test.controllers;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
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
        try {
            ProductoDTO product = productoService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody Producto producto) {
        ProductoDTO product = productoService.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProduct(@RequestBody Producto producto) {
        try {
            ProductoDTO p = productoService.update(producto);
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
