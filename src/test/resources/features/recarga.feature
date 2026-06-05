Feature: Calcular valor de recarga de celular
  Como usuario de RecargaYa
  Quiero calcular el valor final de mi recarga
  Para conocer el total a pagar incluyendo bonificaciones

  Scenario: Rechazar recarga con monto menor al mínimo permitido
    Given que quiero hacer una recarga de 500 pesos
    And no soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser rechazada
    And el mensaje debe ser "Monto invalido: debe estar entre 1000 y 50000"

  Scenario: Rechazar recarga con monto mayor al máximo permitido
    Given que quiero hacer una recarga de 60000 pesos
    And no soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser rechazada
    And el mensaje debe ser "Monto invalido: debe estar entre 1000 y 50000"

  Scenario: Aceptar recarga sin bonificación para montos menores a 10000
    Given que quiero hacer una recarga de 5000 pesos
    And no soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser aceptada
    And la bonificacion debe ser 0 pesos
    And el total debe ser 5000 pesos

  Scenario: Calcular bonificación del 10% para recarga de 10000 pesos
    Given que quiero hacer una recarga de 10000 pesos
    And no soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser aceptada
    And la bonificacion debe ser 1000 pesos
    And el total debe ser 11000 pesos

  Scenario: Calcular bonificación del 25% para recarga de 30000 pesos
    Given que quiero hacer una recarga de 30000 pesos
    And no soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser aceptada
    And la bonificacion debe ser 7500 pesos
    And el total debe ser 37500 pesos

  Scenario: Aplicar bonificación premium adicional del 5%
    Given que quiero hacer una recarga de 10000 pesos
    And soy usuario premium
    When solicito calcular la recarga
    Then la recarga debe ser aceptada
    And la bonificacion debe ser 1050 pesos
    And el total debe ser 11050 pesos

  Scenario Outline: Validar cálculo de recargas con diferentes montos y tipos de usuario
    Given que quiero hacer una recarga de <monto> pesos
    And <tipo_usuario>
    When solicito calcular la recarga
    Then la recarga debe ser <resultado>
    And la bonificacion debe ser <bonificacion> pesos
    And el total debe ser <total> pesos

    Examples:
      | monto | tipo_usuario        | resultado | bonificacion | total |
      | 1000  | no soy usuario premium | aceptada  | 0            | 1000  |
      | 9999  | no soy usuario premium | aceptada  | 0            | 9999  |
      | 10000 | no soy usuario premium | aceptada  | 1000         | 11000 |
      | 29999 | no soy usuario premium | aceptada  | 2999.9       | 32998.9 |
      | 30000 | no soy usuario premium | aceptada  | 7500         | 37500 |
      | 50000 | no soy usuario premium | aceptada  | 12500        | 62500 |
      | 10000 | soy usuario premium    | aceptada  | 1050         | 11050 |
      | 30000 | soy usuario premium    | aceptada  | 7875         | 37875 |
