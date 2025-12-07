package EJ7;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;

public class ArchNiño {
    private String na; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

    public ArchNiño(String na) {
        this.na = na;
    }
    
    // a) Crear/Guardar archivo
    public void crear(ArrayList<Niño> niños) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(na))) {
            gson.toJson(niños, writer); 
            System.out.println("Archivo JSON creado con " + niños.size() + " niños: " + na);
        } catch (IOException e) {
            System.err.println("Error al crear: " + e.getMessage());
        }
    }
    
    // a) Leer archivo
    public ArrayList<Niño> leer() {
        try (Reader reader = Files.newBufferedReader(Paths.get(na))) {
            Niño[] array = gson.fromJson(reader, Niño[].class); 
            return array != null ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // a) Listar
    public void listar() {
        ArrayList<Niño> niños = leer();
        System.out.println("\n=== a) LISTA COMPLETA DE NIÑOS ===");
        niños.forEach(System.out::println);
    }
    
    // e) Buscar niño por carnet
    public void mostrar(int ciCarnet) {
        ArrayList<Niño> ninos = leer();
        System.out.println("\nBuscando niño con carnet (CI): " + ciCarnet);
        ninos.stream()
                .filter(n -> n.getCi() == ciCarnet)
                .findFirst()
                .ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Niño no encontrado.")
                );
    }
    
    // b) Cuántos niños tienen el peso adecuado de acuerdo a su talla y edad
    public void contarPesoTallaAdecuados() {
        ArrayList<Niño> niños = leer();
        long c = niños.stream()
                .filter(n -> n.tienePesoAdecuado() && n.tieneTallaAdecuada())
                .count();
        
        System.out.println("\n=== b) NIÑOS CON PESO Y TALLA ADECUADOS ===");
        System.out.println("Cantidad de niños con peso y talla adecuados: " + c);
    }
    
    // c) Mostrar a los niños que de acuerdo a la edad no tienen el peso o la talla adecuada.
    public void mostrarSinPesoTallaAdecuada() {
        ArrayList<Niño> niños = leer();
        System.out.println("\n=== c) NIÑOS SIN PESO O TALLA ADECUADA ===");
        niños.stream()
                .filter(n -> !n.tienePesoAdecuado() || !n.tieneTallaAdecuada())
                .forEach(n -> {
                    System.out.println(n);
                    if (!n.tienePesoAdecuado()) 
                        System.out.println("  -> Peso inadecuado");
                    if (!n.tieneTallaAdecuada()) 
                        System.out.println("  -> Talla inadecuada");
                });
    }
    
    // d) Determinar el promedio de edad en los niños.
    public void calcularPromedioEdad() {
        ArrayList<Niño> niños = leer();
        if (niños.isEmpty()) {
            System.out.println("No hay niños registrados.");
            return;
        }
        
        OptionalDouble promedio = niños.stream()
                .mapToInt(Niño::getEdad)
                .average();
        
        System.out.println("\n=== d) PROMEDIO DE EDAD ===");
        System.out.println("Promedio de edad: " + 
                           String.format("%.2f", promedio.orElse(0.0)) + " años");
    }
    
    // e) Buscar al niño con el carnet x.
    public void buscarPorCarnet(int ciCarnet) {
        mostrar(ciCarnet);
    }
    
    // f) Mostrar a los niños con la talla más alta.
    public void mostrarMayorTalla() {
        ArrayList<Niño> niños = leer();
        if (niños.isEmpty()) {
            System.out.println("No hay niños registrados.");
            return;
        }
        
        double maxTalla = niños.stream()
                .mapToDouble(Niño::getTalla)
                .max()
                .orElse(0.0);
        
        System.out.println("\n=== f) NIÑOS CON MAYOR TALLA ===");
        System.out.println("Mayor talla registrada: " + maxTalla + " cm");
        niños.stream()
                .filter(n -> n.getTalla() == maxTalla)
                .forEach(System.out::println);
    }
}