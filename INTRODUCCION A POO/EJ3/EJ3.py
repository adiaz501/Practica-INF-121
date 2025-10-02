class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def vender(self, cantidad):
        if cantidad <= self.stock:
            self.stock -= cantidad
            print(f"Se vendieron {cantidad} unidades de {self.nombre}")
            print(f"Stock restante: {self.stock} ")
        else:
            print(f"No hay suficiente stock.")
            print(f"Stock actual: {self.stock}")

    def reabastecer(self, cantidad):
        self.stock += cantidad
        print("----------------------------------------")
        print(f"Se reabastecieron {cantidad} unidades de {self.nombre}")
        print(f"Stock actualizado: {self.stock}")
        print("----------------------------------------")

    def mostrar(self):
        print("----------------------------------------")
        print(f"Producto: {self.nombre}")
        print(f"Precio: Bs {self.precio}")
        print(f"Stock: {self.stock} unidades")
        print("----------------------------------------")


if __name__ == "__main__":
    print("========================================")
    print("----------MI TIENDITA GAMER-------------")
    producto1 = Producto("Laptop", 2500, 10)
    producto1.mostrar()
    producto1.vender(3)
    print("----------------------------------------")
    producto1.vender(8)
    producto1.reabastecer(5)
    producto1.vender(8)

    producto2 = Producto("Mouse", 90, 20)
    producto2.mostrar()
    producto2.vender(13)
    producto2.vender(8)
    producto2.reabastecer(5)
    producto2.vender(10)
    print("========================================")