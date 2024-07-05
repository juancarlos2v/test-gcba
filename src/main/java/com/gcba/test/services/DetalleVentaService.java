package com.gcba.test.services;

import com.gcba.test.dto.RequestDetalle;
import com.gcba.test.dto.ResponseDetalle;
import com.gcba.test.entities.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    DetalleVenta create(RequestDetalle request);
    List<ResponseDetalle> getAll();
    ResponseDetalle getById(Long id);
    ResponseDetalle convertToResponseDetalle(DetalleVenta detalleVenta);
}
