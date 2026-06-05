# Casos de Prueba - RecargaYa S.A.S.

# Reglas de Negocio
- Monto de recarga debe estar entre $1.000 y $50.000
- Recargas de $10.000 o mas reciben 10% de bonificacion en datos
- Recargas de $30.000 o mas reciben 25% de bonificacion en datos
- Usuarios premium obtienen 5% adicional sobre cualquier bonificacion

# Particiones de Equivalencia

| Particion | Descripcion | Rango de Valores | Resultado Esperado |
|------------------------------------------------------------|
| P1 | Monto invalido - menor al minimo | monto < 1000 | Rechazada |
| P2 | Monto invalido - mayor al maximo | monto > 50000 | Rechazada |
| P3 | Monto valido sin bonificacion | 1000 <= monto < 10000 | Aceptada, bonificacion = 0 |
| P4 | Monto valido con bonificacion 10% | 10000 <= monto < 30000 | Aceptada, bonificacion = monto * 0.10 |
| P5 | Monto valido con bonificacion 25% | 30000 <= monto <= 50000 | Aceptada, bonificacion = monto * 0.25 |

# Casos de Prueba con Valores Limite

| ID | Monto | Usuario Premium | Particion | Bonificacion Total | Estado | Descripcion |
|------------------------------------------------------------------------------|
| CP-01 | 999 | No | P1 | 0 | Rechazada | Valor limite inferior - 1 |
| CP-02 | 1000 | No | P3 | 0 | Aceptada | Valor limite inferior exacto |
| CP-03 | 9999 | No | P3 | 0 | Aceptada | Valor limite antes de bonificacion 10% |
| CP-04 | 10000 | No | P4 | 1000 | Aceptada | Inicio bonificacion 10% |
| CP-05 | 29999 | No | P4 | 2999.9 | Aceptada | Valor limite antes de bonificacion 25% |
| CP-06 | 30000 | No | P5 | 7500 | Aceptada | Inicio bonificacion 25% |
| CP-07 | 50000 | No | P5 | 12500 | Aceptada | Valor limite superior exacto |
| CP-08 | 50001 | No | P2 | 0 | Rechazada | Valor limite superior + 1 |
| CP-09 | 10000 | Si | P4 | 1050 | Aceptada | Usuario premium con bonificacion 10% + 5% |
| CP-10 | 30000 | Si | P5 | 7875 | Aceptada | Usuario premium con bonificacion 25% + 5% |
