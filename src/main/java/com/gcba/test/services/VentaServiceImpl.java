package com.gcba.test.services;

import com.gcba.test.dto.RecuentoEstado;
import com.gcba.test.entities.Cliente;
import com.gcba.test.entities.Producto;
import com.gcba.test.entities.Venta;
import com.gcba.test.enums.EstadoVenta;
import com.gcba.test.repositories.ClienteRepository;
import com.gcba.test.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;

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

    @Override
    public List<Venta> getSoldLastDay() {
        Date now = new Date();
        Date last24hours = new Date(now.getTime() - 24 * 60 * 60 * 1000);
        return ventaRepository.findByFechaBetween(last24hours,now);
    }

    @Override
    public List<RecuentoEstado> getRecuento() {
        List<RecuentoEstado> recuentoEstados=new ArrayList<>();
        Integer completada=0;
        Integer pendiente=0;
        Integer cancelada=0;
        List<Venta> ventas=getAll();
        for(Venta venta:ventas){
            if(venta.getEstado().equals(EstadoVenta.COMPLETADA))completada++;
            if(venta.getEstado().equals(EstadoVenta.CANCELADA))cancelada++;
            if (venta.getEstado().equals(EstadoVenta.PENDIENTE))pendiente++;
        }
        recuentoEstados.add(new RecuentoEstado(EstadoVenta.COMPLETADA.name(),completada));
        recuentoEstados.add(new RecuentoEstado(EstadoVenta.PENDIENTE.name(),pendiente));
        recuentoEstados.add(new RecuentoEstado(EstadoVenta.CANCELADA.name(),cancelada));
        return recuentoEstados;
    }
}
