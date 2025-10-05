from multimethod import multimethod

class Videojuego:
    def __init__(self, nombre="Minecraft", plataforma="PC", jugadores=0):
        self.nombre = nombre
        self.plataforma = plataforma
        self.jugadores = jugadores

    @multimethod
    def agregarJugadores(self):
        self.jugadores += 1

    @multimethod
    def agregarJugadores(self, cant: int):
        self.jugadores += cant

    def mostrar(self):
        print(f"Nombre: {self.nombre}")
        print(f"Plataforma: {self.plataforma}")
        print(f"Jugadores: {self.jugadores}")
        print("-" * 20)

j1 = Videojuego()
j2 = Videojuego("FIFA 25", "PS5", 2)

j1.agregarJugadores()
j2.agregarJugadores(3)

j1.mostrar()
j2.mostrar()