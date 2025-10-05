class Pasajero:
    def __init__(self, nombre="", nroHabitacion=0, costoPasaje=0):
        self.nombre = nombre
        self.nroHabitacion = nroHabitacion
        self.costoPasaje = costoPasaje

    def __add__(self, otro):
        self.nombre = input("Nombre: ")
        self.nroHabitacion = int(input("Nro habitación: "))
        self.costoPasaje = float(input("Costo pasaje: "))
        return self

    def __sub__(self, otro):
        print(f"Nombre: {self.nombre}")
        print(f"Habitación: {self.nroHabitacion}")
        print(f"Costo: {self.costoPasaje}")
        return self


class Crucero:
    def __init__(self, nombre="", capacidad=0):
        self.nombre = nombre
        self.capacidad = capacidad
        self.pasajeros = []
        self.nroPasajeros = 0

    def __add__(self, otro):
        self.nombre = input("Nombre crucero: ")
        self.capacidad = int(input("Capacidad: "))
        return self

    def __sub__(self, otro):
        print(f"Crucero: {self.nombre}")
        print(f"Capacidad: {self.capacidad}")
        print(f"Pasajeros: {self.nroPasajeros}")
        return self

    def __eq__(self, otro):
        total = sum(p.costoPasaje for p in self.pasajeros)
        print(f"Total costos: {total}")
        return total

    def agregarPasajero(self, pasajero):
        self.pasajeros.append(pasajero)
        self.nroPasajeros += 1


crucero1 = Crucero()
crucero1 + crucero1

p1 = Pasajero()
p1 + p1
crucero1.agregarPasajero(p1)

p2 = Pasajero()
p2 + p2
crucero1.agregarPasajero(p2)

crucero1 - crucero1
crucero1 == crucero1