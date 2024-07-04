package com.gcba.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_venta")
    private Long id;
    @Column(name="fecha")
    private Date  fecha;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @Column(name="monto_total")
    private Double monto;
    @Column(name="estado")
    private String estado;
}
