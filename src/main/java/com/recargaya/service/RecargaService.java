package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.springframework.stereotype.Service;

@Service
public class RecargaService {

    private static final double MIN_MONTO = 1000;
    private static final double MAX_MONTO = 50000;

    public ResultadoRecarga calcularRecarga(double monto, boolean isPremium) {
        if (monto < MIN_MONTO || monto > MAX_MONTO) {
            return new ResultadoRecarga(false,
                "Monto invalido: debe estar entre " + (int)MIN_MONTO + " y " + (int)MAX_MONTO,
                0, 0);
        }

        return new ResultadoRecarga(true, "Recarga aceptada", monto, 0);
    }
}
