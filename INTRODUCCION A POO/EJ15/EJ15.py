class Carta:
    def __init__(self, codigo, descripcion, remitente, destinatario):
        self.codigo = codigo
        self.descripcion = descripcion
        self.remitente = remitente
        self.destinatario = destinatario

class Buzon:
    def __init__(self, nro):
        self.nro = nro
        self.nroC = 0
        self.c = [["", "", ""]
                  for _ in range(50)]

    def agregar_carta(self, carta):
        if self.nroC < 50:
            self.c[self.nroC][0] = carta.codigo
            self.c[self.nroC][1] = carta.remitente
            self.c[self.nroC][2] = carta.destinatario
            self.nroC += 1
            print(f"Carta {carta.codigo} agregada al buzón {self.nro}")
        else:
            print("Buzón lleno!")

    def mostrar_cartas(self):
        print(f"\nCartas en buzón {self.nro}:")
        for i in range(self.nroC):
            print(f"  {self.c[i][0]}: {self.c[i][1]} -> {self.c[i][2]}")


buzon1 = Buzon("001")
buzon2 = Buzon("002")
buzon3 = Buzon("003")

carta1 = Carta("C123", "Querido amigo te escribo para decirte que ella no te ama por lo tanto...", "Juan Álvarez",
               "Peter Chaves")
carta2 = Carta("C456", "Hola, espero que estés bien. Te extraño mucho mi amor.", "Pepe Mujica", "Wilmer Pérez")
carta3 = Carta("C789", "Necesito hablar contigo sobre nuestro amor.", "Paty Vasques", "Pepe Mujica")
carta4 = Carta("C999", "La reunión será mañana.", "Maria Lopez", "Carlos Ruiz")
carta5 = Carta("C888", "Te amo con todo mi corazón.", "Ana Garcia", "Luis Martinez")
carta6 = Carta("C777", "El paquete llegó hoy.", "Pedro Sanchez", "Laura Diaz")

print("AGREGANDO CARTAS A BUZONES")
buzon1.agregar_carta(carta1)
buzon1.agregar_carta(carta2)
buzon1.agregar_carta(carta3)

buzon2.agregar_carta(carta4)
buzon2.agregar_carta(carta5)
buzon2.agregar_carta(carta6)

buzon3.agregar_carta(carta1)
buzon3.agregar_carta(carta5)

print("\n CONTENIDO DE LOS BUZONES")
buzon1.mostrar_cartas()
buzon2.mostrar_cartas()
buzon3.mostrar_cartas()


def contar_cartas_destinatario(buzones, destinatario):
    contador = 0
    for buzon in buzones:
        for i in range(buzon.nroC):
            if buzon.c[i][2] == destinatario:
                contador += 1
    return contador


destinatario_buscar = "Pepe Mujica"
total = contar_cartas_destinatario([buzon1, buzon2, buzon3], destinatario_buscar)

print(f"El destinatario '{destinatario_buscar}' recibió {total} cartas")


def eliminar_carta_por_codigo(buzones, codigo):
    for buzon in buzones:
        for i in range(buzon.nroC):
            if buzon.c[i][0] == codigo:

                for j in range(i, buzon.nroC - 1):
                    buzon.c[j][0] = buzon.c[j + 1][0]
                    buzon.c[j][1] = buzon.c[j + 1][1]
                    buzon.c[j][2] = buzon.c[j + 1][2]
                buzon.nroC -= 1
                print(f"Carta {codigo} eliminada del buzón {buzon.nro}")
                return True
    print(f"Carta {codigo} no encontrada")
    return False


codigo_eliminar = "C456"
eliminar_carta_por_codigo([buzon1, buzon2, buzon3], codigo_eliminar)

print("\n BUZONES DESPUÉS DE ELIMINAR")
buzon1.mostrar_cartas()


def remitente_recibio_carta(buzones):
    for buzon in buzones:
        for i in range(buzon.nroC):
            remitente = buzon.c[i][1]

            for otro_buzon in buzones:
                for j in range(otro_buzon.nroC):
                    if otro_buzon.c[j][2] == remitente:
                        print(f"El remitente '{remitente}' también recibió carta de '{otro_buzon.c[j][1]}'")
                        return

    print("Ningún remitente recibió cartas")


remitente_recibio_carta([buzon1, buzon2, buzon3])


def buscar_palabra_clave(cartas, palabra):
    print(f"Buscando palabra '{palabra}' en descripciones:")
    encontradas = 0
    for carta in cartas:
        if palabra.lower() in carta.descripcion.lower():
            encontradas += 1
            print(f"  Código: {carta.codigo}")
            print(f"  Remitente: {carta.remitente}")
            print(f"  Destinatario: {carta.destinatario}")
            print(f"  Descripción: {carta.descripcion[:50]}...")
            print()

        if encontradas == 0:
            print(f"No se encontró la palabra '{palabra}'")

        todas_las_cartas = [carta1, carta2, carta3, carta4, carta5, carta6]
        buscar_palabra_clave(todas_las_cartas, "amor")