package com.dariojolo.app.ventas.servicioventas.services;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import com.dariojolo.app.ventas.servicioventas.entities.PreciosValidos;
import com.dariojolo.app.ventas.servicioventas.repositories.FacturaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private RestTemplate clienteRest;

    private final FacturaRespository dao;

    @Override
    public List<Factura> listAll() {
        return dao.findAll();
    }

    @Override
    public Factura findFacturaById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Factura createFactura(Factura factura) {
        PreciosValidos preciosValidos = clienteRest.getForObject("https://servicio-productos.herokuapp.com/api/products/precio/" + factura.getModelo(), PreciosValidos.class);
        if (factura.getPrecio() > preciosValidos.getPrecioMinimo() && factura.getPrecio() < preciosValidos.getPrecioMaximo()) {
            return dao.save(factura);
        }
        return null;
    }

    @Override
    public Factura updateFactura(Factura factura) {
        Factura facturaDB = findFacturaById(factura.getId());
        if (facturaDB == null) {
            return null;
        }
        facturaDB.setModelo(factura.getModelo());
        facturaDB.setPrecio(factura.getPrecio());
        return dao.save(facturaDB);
    }

    @Override
    public void deleteFacturaById(Long id) {
        dao.deleteById(id);
    }
}
