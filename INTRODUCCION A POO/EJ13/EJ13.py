class Fruta:
    def __init__(self, nombre, tipo, vitaminas):
        self.nombre = nombre
        self.tipo = tipo
        self.vitaminas = vitaminas
        self.nroVitaminas = len(vitaminas)


fruta1 = Fruta("kiwi", "subtropical", ["K", "C", "E", "B1"])

vitaminas_naranja = ["C", "A", "B1"]
fruta2 = Fruta("naranja", "cítrica", vitaminas_naranja)

fruta3 = Fruta("fresa", "bosque", ["C", "K", "B9"])

frutas = [fruta1, fruta2, fruta3]

print("LAS 3 FRUTAS")
for fruta in frutas:
    print(f"{fruta.nombre} - {fruta.tipo} - Vitaminas: {fruta.vitaminas}")

fruta_mas_vitaminas = frutas[0]
for fruta in frutas:
    if fruta.nroVitaminas > fruta_mas_vitaminas.nroVitaminas:
        fruta_mas_vitaminas = fruta

print(f"\nLa fruta con más vitaminas es: {fruta_mas_vitaminas.nombre}")
print(f"Tiene {fruta_mas_vitaminas.nroVitaminas} vitaminas")

print(f"\nVitaminas del kiwi: {fruta1.vitaminas}")

contador = 0
for fruta in frutas:
    if "C" in fruta.vitaminas:
        contador += 1

print(f"\nFrutas que tienen vitamina C: {contador}")

print("FRUTAS ORDENADAS POR PRIMERA VITAMINA")

frutas_ordenadas = frutas.copy()

for i in range(len(frutas_ordenadas)):
    for j in range(i + 1, len(frutas_ordenadas)):

        if frutas_ordenadas[i].vitaminas[0] > frutas_ordenadas[j].vitaminas[0]:
            temp = frutas_ordenadas[i]
            frutas_ordenadas[i] = frutas_ordenadas[j]
            frutas_ordenadas[j] = temp

for fruta in frutas_ordenadas:
    print(f"{fruta.nombre}: {fruta.vitaminas[0]}")