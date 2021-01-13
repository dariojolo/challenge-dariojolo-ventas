package com.dariojolo.app.ventas.servicioventas.repositories;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRespository extends JpaRepository<Factura,Long> {
}
