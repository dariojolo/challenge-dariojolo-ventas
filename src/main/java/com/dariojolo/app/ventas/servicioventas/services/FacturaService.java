package com.dariojolo.app.ventas.servicioventas.services;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;

import java.util.List;

public interface FacturaService {
    public List<Factura> listAll();

    public Factura findFacturaById(Long id);

    public Factura createFactura(Factura factura);

    public Factura updateFactura(Factura factura);

    public void deleteFacturaById(Long id);
}
