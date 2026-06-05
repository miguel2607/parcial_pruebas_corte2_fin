package com.recargaya.controller;

import com.recargaya.model.RecargaRequest;
import com.recargaya.model.RecargaResponse;
import com.recargaya.model.ResultadoRecarga;
import com.recargaya.service.RecargaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recargas")
public class RecargaController {

    private final RecargaService recargaService;

    public RecargaController(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    @PostMapping
    public ResponseEntity<RecargaResponse> realizarRecarga(@Valid @RequestBody RecargaRequest request) {
        ResultadoRecarga resultado = recargaService.calcularRecarga(
            request.getMonto(),
            request.getIsPremium()
        );

        RecargaResponse response = RecargaResponse.fromResultadoRecarga(resultado);

        if (resultado.isAceptada()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
