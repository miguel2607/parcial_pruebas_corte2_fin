package com.recargaya.bdd;

import com.recargaya.model.ResultadoRecarga;
import com.recargaya.service.RecargaService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.jupiter.api.Assertions.*;

public class RecargaSteps {

    private RecargaService recargaService;
    private double monto;
    private boolean isPremium;
    private ResultadoRecarga resultado;

    public RecargaSteps() {
        this.recargaService = new RecargaService();
    }

    @Given("que quiero hacer una recarga de {double} pesos")
    public void queQuieroHacerUnaRecargaDePesos(double monto) {
        this.monto = monto;
    }

    @And("no soy usuario premium")
    public void noSoyUsuarioPremium() {
        this.isPremium = false;
    }

    @And("soy usuario premium")
    public void soyUsuarioPremium() {
        this.isPremium = true;
    }

    @When("solicito calcular la recarga")
    public void solicitoCalcularLaRecarga() {
        this.resultado = recargaService.calcularRecarga(monto, isPremium);
    }

    @Then("la recarga debe ser rechazada")
    public void laRecargaDebeSerRechazada() {
        assertFalse(resultado.isAceptada());
    }

    @Then("la recarga debe ser aceptada")
    public void laRecargaDebeSerAceptada() {
        assertTrue(resultado.isAceptada());
    }

    @And("el mensaje debe ser {string}")
    public void elMensajeDebeSer(String mensajeEsperado) {
        assertEquals(mensajeEsperado, resultado.getMensaje());
    }

    @And("la bonificacion debe ser {double} pesos")
    public void laBonificacionDebeSerPesos(double bonificacionEsperada) {
        assertEquals(bonificacionEsperada, resultado.getBonificacion(), 0.01);
    }

    @And("el total debe ser {double} pesos")
    public void elTotalDebeSerPesos(double totalEsperado) {
        assertEquals(totalEsperado, resultado.getTotal(), 0.01);
    }
}
