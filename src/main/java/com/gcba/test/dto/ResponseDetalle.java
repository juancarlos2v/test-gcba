package com.gcba.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalle {
    private Long id;
    private String cliente;
    private String producto;
    private Double precio;
    private Integer cantidad;
    private Double total;


}
