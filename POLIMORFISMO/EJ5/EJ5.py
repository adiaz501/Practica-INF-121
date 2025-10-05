class Celular:
    def __init__(self, nroTel="", dueno="", espacio=0, ram=0, nroApp=0):
        self.nroTel = nroTel
        self.dueno = dueno
        self.espacio = espacio
        self.ram = ram
        self.nroApp = nroApp

    def __add__(self, o):
        self.nroApp += 10
        return self

    def __sub__(self, o):
        self.espacio -= 5
        if self.espacio < 0:
            self.espacio = 0
        return self

    def mostrar(self):
        print(f"Número: {self.nroTel}")
        print(f"Dueño: {self.dueno}")
        print(f"Espacio: {self.espacio} GB")
        print(f"RAM: {self.ram} GB")
        print(f"Apps: {self.nroApp}")

cel = Celular("77123456", "Juan", 64, 8, 20)
print("Antes:")
cel.mostrar()
cel = cel + cel
cel = cel - cel
print("\nDespués:")
cel.mostrar()