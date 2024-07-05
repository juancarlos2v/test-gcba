package com.gcba.test.services;


import com.gcba.test.dto.RecuentoEstado;
import com.gcba.test.entities.Cliente;
import com.gcba.test.entities.Venta;

import java.util.List;

public interface VentaService {
    Venta create(String estado, Double monto, Cliente cliente);
    void delete(Long id);
    List<Venta> getAll();
    List<Venta> getSoldLastDay();
    List<RecuentoEstado> getRecuento();
}
