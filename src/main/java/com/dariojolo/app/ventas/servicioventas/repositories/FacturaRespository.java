package com.dariojolo.app.ventas.servicioventas.repositories;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRespository extends JpaRepository<Factura, Long> {
}
