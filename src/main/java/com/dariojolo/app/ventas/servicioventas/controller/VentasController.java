package com.dariojolo.app.ventas.servicioventas.controller;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import com.dariojolo.app.ventas.servicioventas.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ventas")
public class VentasController {

    @Autowired
    private FacturaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Factura> getFactura(@PathVariable("id") Long id) {
        Factura factura = service.findFacturaById(id);
        if (factura == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(factura);
    }

    @GetMapping
    public ResponseEntity<List<Factura>> listFacturas() {
        List<Factura> facturas = new ArrayList<>();
        facturas = service.listAll();
        if (facturas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
        Factura newFactura = service.createFactura(factura);
        if (newFactura == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newFactura);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable(name = "id") Long id, @RequestBody Factura factura) {
        factura.setId(id);
        Factura facturaDB = service.updateFactura(factura);
        if (facturaDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Factura> deleteFactura(@PathVariable(name = "id") Long id) {
        service.deleteFacturaById(id);
        return ResponseEntity.ok(null);
    }
}
