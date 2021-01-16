package com.dariojolo.app.ventas.servicioventas;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import com.dariojolo.app.ventas.servicioventas.repositories.FacturaRespository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class FacturaRepositoryMockTest {

    @Autowired
    private FacturaRespository facturaRespository;

    @Test
    public void whenFindById_thenReturnProduct() {
        Factura f1 = Factura.builder()
                .nombre("Dario")
                .apellido("Jolodovsky")
                .modelo("Sedan")
                .precio(12345d)
                .descripcionGarantia("Con extras")
                .build();
        facturaRespository.save(f1);

        Factura factura = Factura.builder().build();

        Optional<Factura> found = facturaRespository.findById(f1.getId());
        if (found.isPresent()) {
            factura = found.get();
        }
        Assertions.assertThat(factura.getModelo()).isEqualTo("Sedan");
    }
}
