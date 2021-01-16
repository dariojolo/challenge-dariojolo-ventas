package com.dariojolo.app.ventas.servicioventas;

import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import com.dariojolo.app.ventas.servicioventas.repositories.FacturaRespository;
import com.dariojolo.app.ventas.servicioventas.services.FacturaService;
import com.dariojolo.app.ventas.servicioventas.services.FacturaServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FacturaServiceMockTest {

    @Mock
    private FacturaRespository dao;

    private FacturaService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new FacturaServiceImpl(dao);

        List<Factura> listado = new ArrayList<>();
        listado.add(Factura.builder().id(1l).nombre("Dario").apellido("Jolodovsky").modelo("Sedan").precio(12345d).descripcionGarantia("Garantia extendida").build());
        listado.add(Factura.builder().id(2l).nombre("Carlos").apellido("Sanchez").modelo("Familiar").precio(11223d).descripcionGarantia("Garantia extendida y granizo").build());

        Factura f1 = Factura.builder()
                .id(3L)
                .nombre("Raul")
                .apellido("Quiroga")
                .modelo("Coupe")
                .precio(55555d)
                .descripcionGarantia("3 años")
                .build();

        Mockito.when(dao.findById(3L))
                .thenReturn(Optional.of(f1));
        Mockito.when(dao.save(f1))
                .thenReturn(f1);
        Mockito.when(dao.save(Factura.builder().id(5L).nombre("Martin").apellido("Uriarte").modelo("Sedan").precio(12345d).descripcionGarantia("Garantia extendida").build()))
                .thenReturn(Factura.builder().id(5L).nombre("Martin").apellido("Uriarte").modelo("Sedan").precio(12345d).descripcionGarantia("Garantia extendida").build());
        Mockito.when(dao.findAll())
                .thenReturn(listado);
        Mockito.when(dao.findById(4L))
                .thenReturn(Optional.ofNullable(null));
    }

    @Test
    public void whenValidFindProductByID_ThenReturnProduct() {
        Factura found = service.findFacturaById(3L);
        Assertions.assertThat(found.getModelo().equals("Coupe"));
    }

    @Test
    public void WhenUpdate_ThenReturnUpdatedStock() {
        Factura factura = service.updateFactura(Factura.builder().id(3L).precio(111d).build());
        Assertions.assertThat(factura.getPrecio()).isEqualTo(111d);
    }

    @Test
    public void WhenUpdateNullProduct_ThenReturnNull() {
        Factura factura = service.updateFactura(Factura.builder().id(4L).precio(111d).build());
        Assertions.assertThatNullPointerException();
    }

    @Test
    public void whenFindAll_thenReturnListProduct() {
        List<Factura> founds = service.listAll();
        Assertions.assertThat(founds.size()).isEqualTo(2);
    }

    @Test
    public void whenDeleteFactura_thenReturnNoError() {
        service.deleteFacturaById(1L);
        Assertions.assertThatNoException();
    }
}
