class Fruta:
    def __init__(self, nombre: str, tipo: str, vitaminas: list):
        self.nombre = nombre
        self.tipo = tipo
        self.v = vitaminas
        self.nroVitaminas = len(vitaminas)

    def getVitaminas(self) -> list:
        return self.v

    def es_citrica(self, nombre_vitamina: str) -> bool:
        return nombre_vitamina.upper() == "C"

    def __str__(self):
        return (f"Fruta(Nombre: {self.nombre}, Tipo: {self.tipo}, "
                f"NroVitaminas: {self.nroVitaminas}, Vitaminas: {self.v})")

mandarina = Fruta("Mandarina", "Cítrica", ["C", "A"])

nombre_b = "Plátano"
tipo_b = "Tropical"
vits_b = ["A", "B6", "K", "C"]
platano = Fruta(nombre_b, tipo_b, vits_b)

datos_kiwi = ["Kiwi", "Subtropical", ["E", "C", "K"]]
kiwi = Fruta(*datos_kiwi)

frutas = [mandarina, platano, kiwi]

for fruta in frutas:
    print(fruta)

fruta_mas_vitaminas = mandarina
max_vits = mandarina.nroVitaminas

for fruta in frutas:
    if fruta.nroVitaminas > max_vits:
        max_vits = fruta.nroVitaminas
        fruta_mas_vitaminas = fruta

print(f"La fruta con más vitaminas es: {fruta_mas_vitaminas.nombre}")
print(f"Número de vitaminas: {max_vits}")

fruta_x = kiwi
vitaminas_x = fruta_x.getVitaminas()
print(f"Vitaminas de {fruta_x.nombre}: {', '.join(vitaminas_x)}")

contador_citricas = 0
for fruta in frutas:
    for vitamina in fruta.getVitaminas():
        if fruta.es_citrica(vitamina):
            print(f"Vitamina C encontrada en: {fruta.nombre}")
            contador_citricas += 1
print(f"\nNúmero total de vitaminas 'C' (cítricas) encontradas: {contador_citricas}")

frutas_ordenadas = sorted(frutas, key=lambda fruta: fruta.v[0])
print("Orden de las frutas (según el nombre de su primera vitamina):")
for fruta in frutas_ordenadas:
    print(f"- {fruta.nombre} (Clave de orden: {fruta.v[0]})")