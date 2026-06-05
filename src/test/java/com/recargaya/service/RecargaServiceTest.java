package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecargaServiceTest {

    @Test
    void deberiaRechazarRecargaMenorA1000() {
        RecargaService service = new RecargaService();

        ResultadoRecarga resultado = service.calcularRecarga(999, false);

        assertFalse(resultado.isAceptada());
        assertEquals("Monto invalido: debe estar entre 1000 y 50000", resultado.getMensaje());
    }

    @Test
    void deberiaRechazarRecargaMayorA50000() {
        RecargaService service = new RecargaService();

        ResultadoRecarga resultado = service.calcularRecarga(50001, false);

        assertFalse(resultado.isAceptada());
        assertEquals("Monto invalido: debe estar entre 1000 y 50000", resultado.getMensaje());
    }
}
