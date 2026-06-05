package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.springframework.stereotype.Service;

@Service
public class RecargaService {

    private static final double MIN_MONTO = 1000;
    private static final double MAX_MONTO = 50000;
    private static final double MONTO_BONIFICACION_10_PORCIENTO = 10000;
    private static final double BONIFICACION_10_PORCIENTO = 0.10;
    private static final double MONTO_BONIFICACION_25_PORCIENTO = 30000;
    private static final double BONIFICACION_25_PORCIENTO = 0.25;

    public ResultadoRecarga calcularRecarga(double monto, boolean isPremium) {
        if (monto < MIN_MONTO || monto > MAX_MONTO) {
            return new ResultadoRecarga(false,
                "Monto invalido: debe estar entre " + (int)MIN_MONTO + " y " + (int)MAX_MONTO,
                0, 0);
        }

        double bonificacion = 0;
        if (monto >= MONTO_BONIFICACION_25_PORCIENTO) {
            bonificacion = monto * BONIFICACION_25_PORCIENTO;
        } else if (monto >= MONTO_BONIFICACION_10_PORCIENTO) {
            bonificacion = monto * BONIFICACION_10_PORCIENTO;
        }

        return new ResultadoRecarga(true, "Recarga aceptada", monto, bonificacion);
    }
}
