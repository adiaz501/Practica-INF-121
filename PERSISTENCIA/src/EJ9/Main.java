package EJ9;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArchivoZoo archivo = new ArchivoZoo("zoologicos.dat");
        
        Zoologico zoo1 = new Zoologico(101, "Zoo La Paz", "Mallasa");
        zoo1.agregarAnimal(new Animal("Leo", "León", 2));
        zoo1.agregarAnimal(new Animal("Simba", "León", 1));
        zoo1.agregarAnimal(new Animal("Dumbo", "Elefante", 1));
        zoo1.agregarAnimal(new Animal("Coco", "Mono", 3)); 
        
        Zoologico zoo2 = new Zoologico(102, "Zoo Santa Cruz", "Centro");
        zoo2.agregarAnimal(new Animal("Tigre", "Tigre", 1));
        zoo2.agregarAnimal(new Animal("Pepe", "Loro", 5));
        zoo2.agregarAnimal(new Animal("Nemo", "Pez", 10)); 
        
        Zoologico zoo3 = new Zoologico(103, "Zoo Cochabamba", "Norte");
        
        Zoologico zoo4 = new Zoologico(104, "Zoo Oruro", "Sur");
        zoo4.agregarAnimal(new Animal("Rocky", "Cóndor", 2));
        zoo4.agregarAnimal(new Animal("Sasha", "Llama", 4)); 
        
        ArrayList<Zoologico> zoologicos = new ArrayList<>();
        zoologicos.add(zoo1);
        zoologicos.add(zoo2);
        zoologicos.add(zoo3);
        zoologicos.add(zoo4);
        
        System.out.println("--- 1. CREACIÓN INICIAL ---");
        archivo.crear(zoologicos);
        
        System.out.println("\n=== ZOOLÓGICOS INICIALES ===");
        archivo.leerZoologicos().forEach(System.out::println);
        
        System.out.println("\n--- 2. MODIFICAR ZOO 104 ---");
        archivo.modificar(104, "Zoo Oruro Central", "Casco Viejo");
        
        System.out.println("\n--- 3. ELIMINAR ZOO 102 ---");
        archivo.eliminar(102);
        
        System.out.println("\n=== ESTADO DESPUÉS DE a) ===");
        archivo.leerZoologicos().forEach(System.out::println);
        
        archivo.listarConMayorVariedad(); 
        
        archivo.listarYEliminarVacios(); 
        
        System.out.println("\n=== ESTADO FINAL ANTES DE MOVER ===");
        archivo.leerZoologicos().forEach(System.out::println);
        
        archivo.mostrarAnimalesPorEspecie("León");
        
        archivo.moverAnimales(104, 101);
        
        System.out.println("\n=== ESTADO FINAL (Zoo La Paz con 6 animales y Oruro vacío) ===");
        archivo.leerZoologicos().forEach(z -> {
            System.out.println(z);
        });
    }
}