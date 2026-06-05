package com.recargaya.service;

import com.recargaya.model.ResultadoRecarga;
import org.springframework.stereotype.Service;

@Service
public class RecargaService {

    public ResultadoRecarga calcularRecarga(double monto, boolean isPremium) {
        if (monto < 1000) {
            return new ResultadoRecarga(false, "Monto invalido: debe estar entre 1000 y 50000", 0, 0);
        }

        return new ResultadoRecarga(true, "Recarga aceptada", monto, 0);
    }
}
