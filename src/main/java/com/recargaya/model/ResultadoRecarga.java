package com.recargaya.model;

public class ResultadoRecarga {

    private boolean aceptada;
    private String mensaje;
    private double monto;
    private double bonificacion;
    private double total;

    public ResultadoRecarga() {
    }

    public ResultadoRecarga(boolean aceptada, String mensaje, double monto, double bonificacion) {
        this.aceptada = aceptada;
        this.mensaje = mensaje;
        this.monto = monto;
        this.bonificacion = bonificacion;
        this.total = monto + bonificacion;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
