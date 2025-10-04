class Producto:
    def __init__(self, nombre, precio):
        print(f"Producto '{nombre}' ha sido CREADO")
        self._nombre = nombre
        self._precio = precio

    def getNombre(self):
        return self._nombre

    def getPrecio(self):
        return self._precio

    def setPrecio(self, nuevo_precio):
        if nuevo_precio > 0:
            self._precio = nuevo_precio
        else:
            print("Poner precio valido.")

    def mostrarProducto(self):
        return f"Producto: {self._nombre}, Precio: Bs. {self._precio:.2f}"

    def __del__(self):
        print(f"Producto '{self._nombre}' ha sido ELIMINADO")

class Pedido:

    def __init__(self, idPedido, estadoInicial="Registrado"):
        print(f"Pedido #{idPedido} ha sido CREADO")
        self._idPedido = idPedido
        self._estado = estadoInicial

    def getIdPedido(self):
        return self._idPedido

    def getEstado(self):
        return self._estado

    def setEstado(self, nuevoEstado):
        estadosValidos = ["Registrado", "Preparado", "Entregado"]
        if nuevoEstado in estadosValidos:
            self._estado = nuevoEstado
        else:
            print(f"Estado no válido: {nuevoEstado}")

    def mostrarPedido(self):
        return f"ID Pedido: {self._idPedido}, Estado: {self._estado}"

    def __del__(self):
        print(f"Pedido #{self._idPedido} ha sido ELIMINADO")

print("\n--- INICIO ---")
miCafe = Producto("Café", 5)
miPedido = Pedido(idPedido=101)

print("\n--- INFORMACIÓN INICIAL ---")
print(miCafe.mostrarProducto())
print(miPedido.mostrarPedido())

miCafe.setPrecio(6)
miPedido.setEstado("Preparado")

print(f"Nuevo precio del Café: Bs.{miCafe.getPrecio():.2f}")

print("\n--- INFORMACIÓN FINAL ---")
print(miCafe.mostrarProducto())
print(miPedido.mostrarPedido())

print("\n--- ELIMINACIÓN ---")
del miCafe
del miPedido