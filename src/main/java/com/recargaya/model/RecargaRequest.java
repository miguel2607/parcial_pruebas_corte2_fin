package com.recargaya.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RecargaRequest {

    @NotNull(message = "El monto es requerido")
    @Min(value = 1000, message = "El monto minimo es 1000")
    @Max(value = 50000, message = "El monto maximo es 50000")
    private Double monto;

    @NotNull(message = "El campo isPremium es requerido")
    private Boolean isPremium;

    public RecargaRequest() {
    }

    public RecargaRequest(Double monto, Boolean isPremium) {
        this.monto = monto;
        this.isPremium = isPremium;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }
}
