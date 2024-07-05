package com.gcba.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetalle {
    private Long id_producto;
    private Long id_cliente;
    private Integer cantidad;
}
