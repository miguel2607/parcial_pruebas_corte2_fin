from locust import HttpUser, task, between
import random

class RecargaUser(HttpUser):
    """
    Usuario de Locust que simula peticiones a la API de recargas.

    Prueba de rendimiento:
    - P95 debe ser < 300ms
    - Simula 30 usuarios concurrentes
    - Ejecutar con: locust --host=http://localhost:8080
    """

    # Tiempo de espera entre requests (1-3 segundos)
    wait_time = between(1, 3)

    @task(3)
    def recarga_valida_10000(self):
        """Recarga válida de $10,000 sin premium (bonificación 10%)"""
        self.client.post("/api/recargas", json={
            "monto": 10000,
            "isPremium": False
        })

    @task(3)
    def recarga_valida_30000(self):
        """Recarga válida de $30,000 sin premium (bonificación 25%)"""
        self.client.post("/api/recargas", json={
            "monto": 30000,
            "isPremium": False
        })

    @task(2)
    def recarga_premium(self):
        """Recarga válida con usuario premium"""
        self.client.post("/api/recargas", json={
            "monto": random.choice([10000, 20000, 30000, 40000]),
            "isPremium": True
        })

    @task(1)
    def recarga_sin_bonificacion(self):
        """Recarga válida sin bonificación (< $10,000)"""
        self.client.post("/api/recargas", json={
            "monto": random.randint(1000, 9999),
            "isPremium": False
        })

    @task(1)
    def recarga_invalida_monto_bajo(self):
        """Recarga inválida - monto menor al mínimo (debe retornar 400)"""
        with self.client.post("/api/recargas", json={
            "monto": random.randint(100, 999),
            "isPremium": False
        }, catch_response=True) as response:
            if response.status_code == 400:
                response.success()  # 400 es esperado, marcar como éxito
            else:
                response.failure(f"Se esperaba 400 pero se recibió {response.status_code}")

    @task(1)
    def recarga_invalida_monto_alto(self):
        """Recarga inválida - monto mayor al máximo (debe retornar 400)"""
        with self.client.post("/api/recargas", json={
            "monto": random.randint(50001, 100000),
            "isPremium": False
        }, catch_response=True) as response:
            if response.status_code == 400:
                response.success()  # 400 es esperado, marcar como éxito
            else:
                response.failure(f"Se esperaba 400 pero se recibió {response.status_code}")
