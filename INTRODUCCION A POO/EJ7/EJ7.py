class Mascota:
    def __init__(self, nombre, tipo, energia):
        self.nombre = nombre
        self.tipo = tipo
        self.energia = energia

    def alimentar(self):
        if self.energia <= 80:
            self.energia += 20
            print(f"{self.nombre} fue alimentado")
            print(f"Energía actual: {self.energia}%")
        else:
            self.energia = 100
            print(f"{self.nombre} está llenito")
            print(f"Energía actual: {self.energia}%")


    def jugar(self):
        if self.energia >= 15:
            self.energia -= 15
            print(f"{self.nombre} jugó")
            print(f"Energía actual: {self.energia}%")
        else:
            self.energia <= 15
            print(f"{self.nombre} está agotado")
            print(f"Energía: {self.energia}%")

    def mostrar(self):
        print(f"Nombre: {self.nombre}")
        print(f"Tipo: {self.tipo}")
        print(f"Energía: {self.energia}%")


if __name__ == "__main__":
    mascota1 = Mascota("Baeyli", "Perro", 0)
    mascota2 = Mascota("Pardo", "Perro", 90)

    print("=============ESTADO INICIAL ============")
    print("----------------------------------------")
    mascota1.mostrar()
    print("----------------------------------------")
    mascota2.mostrar()
    print("----------------------------------------")

    print("\n======== ALIMENTANDO MASCOTAS ========")
    mascota1.alimentar()
    mascota2.alimentar()

    print("\n ============= JUEGO 1 ===============")
    mascota1.jugar()
    mascota2.jugar()

    print("\n ============= JUEGO 2 ===============")
    mascota1.jugar()
    mascota2.jugar()

    print("\n =========== ESTADO FINAL ============")
    mascota1.mostrar()
    print("----------------------------------------")
    mascota2.mostrar()