package com.gcba.test.controllers;

import com.gcba.test.dto.RecuentoEstado;
import com.gcba.test.entities.Venta;
import com.gcba.test.services.VentaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaController {

    private final VentaServiceImpl ventaService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id) {
        ventaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Venta eliminada");
    }

    @GetMapping("/estados")
    public List<RecuentoEstado> recuentoEstados(){
        return ventaService.getRecuento();
    }

}
