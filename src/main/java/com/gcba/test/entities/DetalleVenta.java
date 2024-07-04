package com.gcba.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalleVenta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto productos;

    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio_unitario")
    private Double precio;

}
