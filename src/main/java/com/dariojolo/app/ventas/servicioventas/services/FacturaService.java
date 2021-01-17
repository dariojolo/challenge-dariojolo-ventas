package com.dariojolo.app.ventas.servicioventas.services;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;

import java.util.List;

public interface FacturaService {
    List<Factura> listAll();

    Factura findFacturaById(Long id);

    Factura createFactura(Factura factura);

    Factura updateFactura(Factura factura);

    void deleteFacturaById(Long id);
}
