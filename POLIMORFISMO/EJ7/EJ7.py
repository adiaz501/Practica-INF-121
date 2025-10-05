from multimethod import multimethod

class Admin:
    def __init__(self, nombre="", cargo=""):
        self.nombre = nombre
        self.cargo = cargo

class Auto:
    def __init__(self, marca="", modelo="", color=""):
        self.marca = marca
        self.modelo = modelo
        self.color = color


class Parqueo:
    def __init__(self, nombre="", direccion=""):
        self.nombre = nombre
        self.direccion = direccion
        self.admins = []
        self.autos = []
        self.nroAdmins = 0
        self.nroAutos = 0

    @multimethod
    def adicionar(self, admin: Admin):
        self.admins.append(admin)
        self.nroAdmins += 1
        print(f"Admin {admin.nombre} agregado")

    @multimethod
    def adicionar(self, marca: str, modelo: str, color: str):
        auto = Auto(marca, modelo, color)
        self.autos.append(auto)
        self.nroAutos += 1
        print(f"Auto {marca} {modelo} agregado")

    def mostrar(self):
        print(f"Parqueo: {self.nombre}")
        print(f"Dirección: {self.direccion}")
        print(f"Admins: {self.nroAdmins}")
        print(f"Autos: {self.nroAutos}")


parqueo = Parqueo("Central", "Av. 6 de Agosto")
admin1 = Admin("Pedro", "Supervisor")
parqueo.adicionar(admin1)
parqueo.adicionar("Toyota", "Corolla", "Rojo")
parqueo.mostrar()