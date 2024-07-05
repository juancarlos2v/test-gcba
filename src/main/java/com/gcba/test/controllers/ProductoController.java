package com.gcba.test.controllers;

import com.gcba.test.dto.ProductoDTO;
import com.gcba.test.entities.Producto;
import com.gcba.test.services.ProductoService;
import com.gcba.test.services.ProductoServiceImpl;
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
    public ProductoDTO getProduct(@PathVariable Long id){
        return  productoService.getById(id);
    }

    @GetMapping("/sin-descripcion")
    public List<Producto> getProductSinDescripcion(){
        return productoService.productosSinDescripcion();
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody Producto producto) {
        ProductoDTO p = productoService.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProduct(@RequestBody Producto producto) {
        ProductoDTO p = productoService.update(producto);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity .status(HttpStatus.NO_CONTENT).body("Producto Eliminado" );
    }

    @GetMapping("/ultimas-24-horas")
    public List<Producto> getProductsUltimas24Horas() {
        return productoService.getProductsSoldLastDay();
    }
}
