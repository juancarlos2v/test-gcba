package com.gcba.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalle {
    private Long id;
    private Long id_venta;
    private Long id_producto;
    private String producto;
    private Double precio;
    private Integer cantidad;


}
