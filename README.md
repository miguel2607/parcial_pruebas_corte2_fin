# RecargaYa - Sistema de Cálculo de Recargas

Sistema de cálculo de recargas de celular con bonificaciones, desarrollado con TDD, Spring Boot y pruebas BDD.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Reglas de Negocio](#reglas-de-negocio)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Ejecución de Pruebas](#ejecución-de-pruebas)
- [API REST](#api-rest)
- [CI/CD](#cicd)
- [Estructura del Proyecto](#estructura-del-proyecto)

## Descripción

RecargaYa S.A.S. es un sistema que calcula el valor final de recargas de celular aplicando bonificaciones según el monto y el tipo de usuario.

## Reglas de Negocio

1. **Montos Válidos**: Entre $1,000 y $50,000
2. **Bonificaciones por Monto**:
   - $1,000 - $9,999: Sin bonificación
   - $10,000 - $29,999: 10% de bonificación
   - $30,000 - $50,000: 25% de bonificación
3. **Bonificación Premium**: Usuarios premium reciben un 5% adicional sobre la bonificación base

## Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Maven 3.x**
- **JUnit 5** - Tests unitarios
- **Cucumber + Gherkin** - Tests BDD
- **Locust** - Pruebas de rendimiento
- **JaCoCo** - Cobertura de código
- **OWASP ZAP** - Pruebas de seguridad
- **GitHub Actions** - CI/CD

## Requisitos

- JDK 17 o superior
- Maven 3.6 o superior
- Python 3.8+ (para pruebas de rendimiento)
- Git

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/miguel2607/parcial_pruebas_corte2_fin.git
cd parcial_pruebas_corte2_fin
```

2. Compilar el proyecto:
```bash
mvn clean install
```

## Ejecución de Pruebas

### Tests Unitarios (JUnit)

Ejecutar solo los tests unitarios:
```bash
mvn clean test
```

Resultado esperado: 10 tests pasando

### Tests BDD (Cucumber)

Ejecutar tests de integración BDD:
```bash
mvn clean verify
```

Resultado esperado:
- 10 tests unitarios
- 14 tests BDD (6 escenarios + 8 del Scenario Outline)

### Reporte de Cobertura (JaCoCo)

Generar reporte de cobertura:
```bash
mvn clean verify
```

Ver reporte en: `target/site/jacoco/index.html`

### Pruebas de Rendimiento (Locust)

1. Instalar Locust:
```bash
pip install -r requirements.txt
```

2. Iniciar la aplicación:
```bash
mvn spring-boot:run
```

3. En otra terminal, ejecutar Locust:
```bash
# Modo headless (sin interfaz)
locust --headless --users 30 --spawn-rate 5 --run-time 60s --host http://localhost:8080

# Modo con interfaz web
locust --host http://localhost:8080
# Abrir http://localhost:8089 en el navegador
```

**Criterio de aceptación**: P95 < 300ms con 30 usuarios concurrentes

### Pruebas de Seguridad (OWASP ZAP)

Las pruebas de seguridad se ejecutan automáticamente en el pipeline de CI/CD. Para ejecutarlas localmente:

1. Instalar OWASP ZAP
2. Iniciar la aplicación:
```bash
mvn spring-boot:run
```
3. Ejecutar escaneo ZAP contra `http://localhost:8080`

## API REST

### Endpoint Principal

**POST** `/api/recargas`

#### Request Body
```json
{
  "monto": 10000,
  "isPremium": false
}
```

#### Validaciones
- `monto`: Requerido, entre 1000 y 50000
- `isPremium`: Requerido, booleano

#### Response Exitoso (200 OK)
```json
{
  "aceptada": true,
  "mensaje": "Recarga aceptada",
  "monto": 10000.0,
  "bonificacion": 1000.0,
  "total": 11000.0
}
```

#### Response Error de Validación (400 Bad Request)
```json
{
  "monto": "El monto minimo es 1000"
}
```

### Ejemplos de Uso

#### Recarga de $10,000 sin premium
```bash
curl -X POST http://localhost:8080/api/recargas \
  -H "Content-Type: application/json" \
  -d '{"monto": 10000, "isPremium": false}'
```

Respuesta: Bonificación de $1,000 (10%)

#### Recarga de $30,000 con premium
```bash
curl -X POST http://localhost:8080/api/recargas \
  -H "Content-Type: application/json" \
  -d '{"monto": 30000, "isPremium": true}'
```

Respuesta: Bonificación de $7,875 (25% + 5% premium)

## CI/CD

El proyecto incluye un pipeline de GitHub Actions que se ejecuta en cada push:

### Etapas del Pipeline

1. **Tests Unitarios** (JUnit)
   - Ejecuta 10 tests unitarios
   - Genera reporte de cobertura JaCoCo

2. **Tests BDD** (Cucumber)
   - Ejecuta 14 escenarios BDD
   - Genera reporte HTML de Cucumber

3. **Pruebas de Seguridad** (OWASP ZAP)
   - Escaneo de vulnerabilidades
   - Baseline scan del API

4. **Pruebas de Rendimiento** (Locust)
   - 30 usuarios concurrentes
   - Verifica P95 < 300ms
   - Genera reporte HTML

### Ver Estado del Pipeline

[![CI Pipeline](https://github.com/miguel2607/parcial_pruebas_corte2_fin/actions/workflows/ci.yml/badge.svg)](https://github.com/miguel2607/parcial_pruebas_corte2_fin/actions)

## Estructura del Proyecto

```
parcial_pruebas_corte2_fin/
├── .github/
│   └── workflows/
│       └── ci.yml                 # Pipeline de GitHub Actions
├── docs/
│   └── casos-de-prueba.md         # Tabla de casos de prueba
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/recargaya/
│   │   │       ├── controller/
│   │   │       │   └── RecargaController.java
│   │   │       ├── exception/
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       ├── model/
│   │   │       │   ├── RecargaRequest.java
│   │   │       │   ├── RecargaResponse.java
│   │   │       │   └── ResultadoRecarga.java
│   │   │       ├── service/
│   │   │       │   └── RecargaService.java
│   │   │       └── RecargaYaApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/recargaya/
│       │       ├── bdd/
│       │       │   ├── CucumberIT.java
│       │       │   ├── CucumberSpringConfiguration.java
│       │       │   └── RecargaSteps.java
│       │       └── service/
│       │           └── RecargaServiceTest.java
│       └── resources/
│           └── features/
│               └── recarga.feature
├── locustfile.py                  # Script de pruebas de rendimiento
├── requirements.txt               # Dependencias Python
├── pom.xml                        # Configuración Maven
└── README.md                      # Este archivo
```

## Casos de Prueba

Ver documentación detallada de casos de prueba en: `docs/casos-de-prueba.md`

## Commits TDD

El proyecto fue desarrollado siguiendo TDD estricto. Ver historial de commits para observar los ciclos RED-GREEN-REFACTOR:

```bash
git log --oneline
```



