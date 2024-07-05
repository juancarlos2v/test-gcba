package com.gcba.test.controllers;

import com.gcba.test.dto.RequestDetalle;
import com.gcba.test.dto.ResponseDetalle;
import com.gcba.test.entities.DetalleVenta;
import com.gcba.test.services.DetalleVentaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
@AllArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaServiceImpl detalleVentaService;

    @GetMapping
    public List<ResponseDetalle> getAll() {
        return detalleVentaService.getAll();
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> addDetalle(@RequestBody RequestDetalle request) {
        DetalleVenta dv= detalleVentaService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dv);
    }

}
