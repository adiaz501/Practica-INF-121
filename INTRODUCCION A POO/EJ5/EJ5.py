class Persona:
    def __init__(self, nombre, paterno, materno, edad, ci):
        self.nombre = nombre
        self.paterno = paterno
        self.materno = materno
        self.edad = edad
        self.ci = ci

    def mostrarDatos(self):
        print(f"Nombre: {self.nombre}")
        print(f"Apellido Paterno: {self.paterno}")
        print(f"Apellido Materno: {self.materno}")
        print(f"Edad: {self.edad} años")
        print(f"CI: {self.ci}")
        print("----------------------------------------")

    def mayorDeEdad(self):
        if self.edad >= 18:
            return (f"es mayor de edad")
        else:
            return (f"es menor de edad")

    def modificarEdad(self, nuevo):
        self.edad = nuevo
        print(f"Edad modificada a: {self.edad} años")

    def mismoPaterno(self, otraPersona):
        return self.paterno == otraPersona.paterno


if __name__ == "__main__":

    persona1 = Persona("Jose", "Torrez", "Mendez", 15, 9909760)
    persona2 = Persona("Moises", "Torrez", "Prado", 30, 9999760)
    persona3 = Persona("Rilberth", "Choque", "Aliaga", 27, 8789760)

    print("========================================")
    print("--------- DATOS DE LAS PERSONAS --------")
    persona1.mostrarDatos()
    persona2.mostrarDatos()
    persona3.mostrarDatos()

    print("========================================")
    print(f"{persona1.nombre} : {persona1.mayorDeEdad()}")
    print(f"{persona2.nombre} : {persona2.mayorDeEdad()}")
    print(f"{persona3.nombre} : {persona3.mayorDeEdad()}")

    print("========================================")
    persona1.modificarEdad(20)
    print(f"Ahora {persona1.nombre} tiene : {persona1.edad} años")
    print(f"{persona1.nombre} : {persona1.mayorDeEdad()}")

    print("========================================")
    print("\n=== VERIFICACIÓN DE APELLIDO PATERNO ===")
    if persona1.mismoPaterno(persona2):
        print(f"{persona1.nombre} y {persona2.nombre} tienen el mismo apellido paterno")
    else:
        print(f"{persona1.nombre} y {persona2.nombre} no tienen el mismo apellido paterno")

    print("========================================")
    print("\n=== VERIFICACIÓN DE APELLIDO PATERNO ===")
    if persona2.mismoPaterno(persona3):
        print(f"{persona2.nombre} y {persona3.nombre} tienen el mismo apellido paterno")
    else:
        print(f"{persona2.nombre} y {persona3.nombre} no tienen el mismo apellido paterno")

    print("========================================")