package com.gcba.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="nomre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private Double precio;
    @Column(name="cantidad")
    private Integer cantidad;

}
