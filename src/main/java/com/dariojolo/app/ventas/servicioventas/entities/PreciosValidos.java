package com.dariojolo.app.ventas.servicioventas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreciosValidos {
    private double precioMinimo;
    private double precioMaximo;
}
