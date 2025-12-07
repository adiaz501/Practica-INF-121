package EJ4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArchivoNota archivo = new ArchivoNota("notas.json");
        
        Estudiante e1 = new Estudiante("202012345", "Juan", "Pérez", "López", 20);
        Estudiante e2 = new Estudiante("202012346", "María", "García", "Martínez", 21);
        Estudiante e3 = new Estudiante("202012347", "Carlos", "Rodríguez", "Sánchez", 19);
        Estudiante e4 = new Estudiante("202012348", "Ana", "Fernández", "Torres", 22);
        
        ArrayList<Nota> notas = new ArrayList<>();
        notas.add(new Nota("Programación II", 85.5, e1));
        notas.add(new Nota("Programación II", 92.0, e2));
        notas.add(new Nota("Base de Datos", 78.5, e3));
        notas.add(new Nota("Programación II", 88.0, e4));
        
        
        archivo.escribirNotas(notas); 
        
        System.out.println("=== TODAS LAS NOTAS DESPUÉS DE LA CARGA INICIAL ===");
        archivo.mostrarTodas();
        
        System.out.println("\n=== b) AGREGAR MÁS ESTUDIANTES ===");
        ArrayList<Nota> nuevas = new ArrayList<>();
        Estudiante e5 = new Estudiante("202012349", "Luis", "Mendoza", "Vargas", 20);
        Estudiante e6 = new Estudiante("202012350", "Sofía", "Ramos", "Cruz", 21);
        nuevas.add(new Nota("Base de Datos", 92.0, e5)); 
        nuevas.add(new Nota("Física", 70.0, e1));
        nuevas.add(new Nota("Electronica", 90.0, e6));
        archivo.agregarEstudiantes(nuevas);
        
        System.out.println("\n=== c) PROMEDIO DE NOTAS ===");
        archivo.calcularPromedioNotas();
        
        System.out.println("\n=== d) ESTUDIANTE(S) CON MEJOR NOTA ===");
        archivo.buscarMejorNota();
        
        System.out.println("\n=== e) ELIMINAR ESTUDIANTES ===");
        archivo.eliminarPorMateria("Programación II");
        
        System.out.println("\n=== RESTANTES ===");
        archivo.mostrarTodas();
    }
}