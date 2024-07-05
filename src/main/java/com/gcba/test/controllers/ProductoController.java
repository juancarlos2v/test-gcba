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
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<Producto> getAllProducts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductoDTO getProduct(@PathVariable Long id){
        return  service.getById(id);
    }

    @GetMapping("/sin-descripcion")
    public List<Producto> getProductSinDescripcion(){
        return service.productosSinDescripcion();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody Producto producto) {
        ProductoDTO p = service.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProduct(@RequestBody Producto producto) {
        ProductoDTO p = service.update(producto);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity .status(HttpStatus.NO_CONTENT).body("Producto Eliminado" );
    }
}
