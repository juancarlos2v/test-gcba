package com.gcba.test.services;

import com.gcba.test.entities.Cliente;
import com.gcba.test.entities.Venta;
import com.gcba.test.enums.EstadoVenta;
import com.gcba.test.repositories.ClienteRepository;
import com.gcba.test.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;


    @Transactional
    @Override
    public Venta create(String estado, Double monto, Cliente cliente) {
        Venta v = Venta.builder()
                .estado(EstadoVenta.valueOf(estado))
                .monto(monto)
                .cliente(cliente)
                .build();
        ventaRepository.save(v);
        return v;
    }

    @Override
    public void delete(Long id) {
        Venta v=ventaRepository.findById(id).orElse(null);
        ventaRepository.delete(v);
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }


}
