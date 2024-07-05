package com.gcba.test.services;

import com.gcba.test.dto.RequestDetalle;
import com.gcba.test.dto.ResponseDetalle;
import com.gcba.test.entities.Cliente;
import com.gcba.test.entities.DetalleVenta;
import com.gcba.test.entities.Producto;
import com.gcba.test.entities.Venta;
import com.gcba.test.enums.EstadoVenta;
import com.gcba.test.repositories.ClienteRepository;
import com.gcba.test.repositories.DetalleVentaRepository;
import com.gcba.test.repositories.ProductoRepository;
import com.gcba.test.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ClienteRepository clienteRepository;
    private final VentaServiceImpl ventaService;

    @Transactional
    @Override
    public DetalleVenta create(RequestDetalle request) {
        Producto p = productoRepository.findById(request.getId_producto()).orElse(null);
        Cliente c = clienteRepository.findById(request.getId_cliente()).orElse(null);

        Double total = request.getCantidad() * p.getPrecio();

        Venta v= ventaService.create("PENDIENTE",total,c);

        DetalleVenta dv= DetalleVenta.builder()
                .cantidad(request.getCantidad())
                .precio(p.getPrecio())
                .producto(p)
                .venta(v)
                .build();

        detalleVentaRepository.save(dv);
        return dv;
    }

    @Override
    public List<ResponseDetalle> getAll() {
        List<DetalleVenta> detalles = detalleVentaRepository.findAll();

        return detalles.stream()
                .map(this::convertToResponseDetalle)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ResponseDetalle getById(Long id) {
        DetalleVenta detalleVenta=detalleVentaRepository.findById(id).orElse(null);
        return convertToResponseDetalle(detalleVenta);
    }


    @Override
    public ResponseDetalle convertToResponseDetalle(DetalleVenta detalleVenta){
        Venta venta= detalleVenta.getVenta();
        Producto producto=detalleVenta.getProducto();
        Cliente client=venta.getCliente();
        return ResponseDetalle.builder()
                .id(detalleVenta.getId())
                .cliente(client.getNombre())
                .producto(producto.getNombre())
                .precio(producto.getPrecio())
                .cantidad(detalleVenta.getCantidad())
                .total(venta.getMonto())
                .build();
    }
}
