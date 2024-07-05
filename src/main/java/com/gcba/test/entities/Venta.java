package com.gcba.test.entities;

import com.gcba.test.enums.EstadoVenta;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long id;

    @Column(name="fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date  fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name="monto_total")
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name="estado")
    private EstadoVenta estado;

    @PrePersist
    protected void onCreate(){
        this.fecha = new Date();
    }
}
