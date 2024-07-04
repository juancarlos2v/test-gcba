package com.gcba.test.controllers;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
import com.gcba.test.services.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping("/productos")
    public List<Producto> getAllProducts() {
        return service.getAll();
    }

    @GetMapping("/producto/{id}")
    public ProductoDTO getProduct(@PathVariable Long id){
        return  service.getById(id);
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody Producto producto) {
        ProductoDTO p = service.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/producto")
    public ResponseEntity<ProductoDTO> updateProduct(@RequestBody Producto producto) {
        ProductoDTO p = service.update(producto);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity .status(HttpStatus.NO_CONTENT).body("Producto Eliminado" );
    }
}
