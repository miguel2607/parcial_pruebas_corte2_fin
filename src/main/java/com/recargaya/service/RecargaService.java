package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.springframework.stereotype.Service;

@Service
public class RecargaService {

    private static final double MIN_MONTO = 1000;
    private static final double MAX_MONTO = 50000;
    private static final double TIER1_MONTO = 10000;
    private static final double TIER1_BONIFICACION = 0.10;

    public ResultadoRecarga calcularRecarga(double monto, boolean isPremium) {
        if (monto < MIN_MONTO || monto > MAX_MONTO) {
            return new ResultadoRecarga(false,
                "Monto invalido: debe estar entre " + (int)MIN_MONTO + " y " + (int)MAX_MONTO,
                0, 0);
        }

        double bonificacion = 0;
        if (monto >= TIER1_MONTO) {
            bonificacion = monto * TIER1_BONIFICACION;
        }

        return new ResultadoRecarga(true, "Recarga aceptada", monto, bonificacion);
    }
}
