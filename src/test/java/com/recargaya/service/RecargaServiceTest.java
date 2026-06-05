package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecargaServiceTest {

    private RecargaService service;

    @BeforeEach
    void setUp() {
        service = new RecargaService();
    }

    @Test
    void deberiaRechazarRecargaMenorA1000() {

        ResultadoRecarga resultado = service.calcularRecarga(999, false);

        assertFalse(resultado.isAceptada());
        assertEquals("Monto invalido: debe estar entre 1000 y 50000", resultado.getMensaje());
    }

    @Test
    void deberiaRechazarRecargaMayorA50000() {
        ResultadoRecarga resultado = service.calcularRecarga(50001, false);

        assertFalse(resultado.isAceptada());
        assertEquals("Monto invalido: debe estar entre 1000 y 50000", resultado.getMensaje());
    }

    @Test
    void deberiaAplicarBonificacion10PorCientoEn10000() {
        ResultadoRecarga resultado = service.calcularRecarga(10000, false);

        assertTrue(resultado.isAceptada());
        assertEquals(1000, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAplicarBonificacion25PorCientoEn30000() {
        ResultadoRecarga resultado = service.calcularRecarga(30000, false);

        assertTrue(resultado.isAceptada());
        assertEquals(7500, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAplicarBonificacionPremiumAdicional5PorCiento() {
        ResultadoRecarga resultado = service.calcularRecarga(10000, true);

        assertTrue(resultado.isAceptada());
        assertEquals(1050, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAceptarRecargaEn1000SinBonificacion() {
        ResultadoRecarga resultado = service.calcularRecarga(1000, false);

        assertTrue(resultado.isAceptada());
        assertEquals(0, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAceptarRecargaEn9999SinBonificacion() {
        ResultadoRecarga resultado = service.calcularRecarga(9999, false);

        assertTrue(resultado.isAceptada());
        assertEquals(0, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAplicarBonificacion10PorCientoEn29999() {
        ResultadoRecarga resultado = service.calcularRecarga(29999, false);

        assertTrue(resultado.isAceptada());
        assertEquals(2999.9, resultado.getBonificacion(), 0.01);
    }

    @Test
    void deberiaAplicarBonificacion25PorCientoEn50000() {
        ResultadoRecarga resultado = service.calcularRecarga(50000, false);

        assertTrue(resultado.isAceptada());
        assertEquals(12500, resultado.getBonificacion(), 0.01);
    }
}
