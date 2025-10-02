class Bus:
    def __init__(self, capacidad):
        self.capacidad = capacidad
        self.pasajerosActuales = 0
        self.precioPasaje = 1.50

    def subirPasajeros(self, cantidad):
        asientosLibres = self.capacidad - self.pasajerosActuales
        if cantidad <= asientosLibres:
            self.pasajerosActuales += cantidad
            print(f"Subieron {cantidad} pasajeros")
        else:
            self.pasajerosActuales += asientosLibres
            print(f"Solo pudieron subir {asientosLibres} pasajeros")
            print("Bus lleno")

    def cobrarPasaje(self):
        if self.pasajerosActuales > 0:
            montoCobrado = self.pasajerosActuales * self.precioPasaje
            print(f"Se cobraron {self.pasajerosActuales} pasajes")
            print(f"Monto recaudado: Bs. {montoCobrado:.2f}")
        else:
            print("No hay pasajeros")

    def asientosDisponibles(self):
        disponibles = self.capacidad - self.pasajerosActuales
        print(f"{disponibles} asientos disponibles de {self.capacidad}")
        return disponibles

    def mostrar(self):
        print("=====================================")
        print("------------INFORMACIÓN--------------")
        print("=====================================")
        print(f"Capacidad total: {self.capacidad} pasajeros")
        print(f"Pasajeros actuales: {self.pasajerosActuales} pasajeros")
        print(f"Asientos disponibles: {self.capacidad - self.pasajerosActuales}")
        print(f"Precio del pasaje: Bs. {self.precioPasaje:.2f}")
        print("-------------------------------------")

if __name__ == "__main__":
    print("=====================================")
    print("----------GESTIÓN DE BUS-------------")

    miBus = Bus(capacidad=50)
    miBus.mostrar()

    print("-----------PRIMERA PARADA------------")
    miBus.subirPasajeros(20)
    miBus.cobrarPasaje()
    miBus.asientosDisponibles()

    print("-------------------------------------")
    print("-----------SEGUNDA PARADA------------")
    miBus.subirPasajeros(15)
    miBus.cobrarPasaje()
    miBus.asientosDisponibles()

    print("-------------------------------------")
    print("-----------TERCERA PARADA------------")
    miBus.subirPasajeros(20)
    miBus.cobrarPasaje()
    miBus.asientosDisponibles()
    print("=====================================")




