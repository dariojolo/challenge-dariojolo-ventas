package com.dariojolo.app.ventas.servicioventas;

import com.dariojolo.app.ventas.servicioventas.controller.VentasController;
import com.dariojolo.app.ventas.servicioventas.entities.Factura;
import com.dariojolo.app.ventas.servicioventas.services.FacturaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VentasController.class)
public class VentasControllerMockTest {

    @MockBean
    private FacturaService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockmvc() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void testGetFactura() throws Exception {
        Mockito.when(service.findFacturaById(1L)).thenReturn(Factura.builder().id(1L).precio(111d).build());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/ventas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testListFacturas() throws Exception {
        Mockito.when(service.listAll()).thenReturn(List.of(Factura.builder().id(1L).precio(111d).build(), Factura.builder().id(2L).modelo("Sedan").precio(111d).build()));

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/ventas/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value("Sedan"));
    }
}
