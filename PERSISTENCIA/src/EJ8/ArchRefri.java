package EJ8;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class ArchRefri {
    private String nombre; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

    public ArchRefri(String nombreArchivo) {
        this.nombre = nombreArchivo;
    }
    
    private void guardar(ArrayList<Alimento> alimentos) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(nombre))) {
            gson.toJson(alimentos, writer); 
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }
    
    public ArrayList<Alimento> leer() {
        try (Reader reader = Files.newBufferedReader(Paths.get(nombre))) {
            Alimento[] array = gson.fromJson(reader, Alimento[].class); 
            return array != null ? new ArrayList<>(Arrays.asList(array)) : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>(); 
        } catch (JsonSyntaxException e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // a) Crear
    public void crear(ArrayList<Alimento> alimentos) {
        guardar(alimentos);
        System.out.println("Archivo JSON creado/actualizado con " + alimentos.size() + " alimentos: " + nombre);
    }
    
    // a) Modificar por nombre
    public void modificarPorNombre(String nombre, Date nuevaFecha, int nuevaCantidad) {
        ArrayList<Alimento> alimentos = leer();
        boolean encontrado = false;
        
        for (Alimento a : alimentos) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                a.setFechaVencimiento(nuevaFecha);
                a.setCantidad(nuevaCantidad);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado) {
            guardar(alimentos);
            System.out.println("Alimento '" + nombre + "' modificado.");
        } else {
            System.out.println("Alimento no encontrado para modificar.");
        }
    }
    
    // a) Eliminar por nombre
    public void eliminarPorNombre(String nombre) {
        ArrayList<Alimento> alimentos = leer();
        int tamInicial = alimentos.size();
        
        alimentos.removeIf(a -> a.getNombre().equalsIgnoreCase(nombre));
        
        if (alimentos.size() < tamInicial) {
            guardar(alimentos);
            System.out.println("Alimento '" + nombre + "' eliminado.");
        } else {
            System.out.println("Alimento no encontrado para eliminar.");
        }
    }
    
    // b) Mostrar alimentos que caducaron antes de una fecha dada X
    public void mostrarCaducadosAntesDe(Date fecha) {
        ArrayList<Alimento> alimentos = leer();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("\n=== b) ALIMENTOS CADUCADOS ANTES DE " + sdf.format(fecha) + " ===");
        alimentos.stream()
                .filter(a -> a.getFechaVencimiento().before(fecha))
                .forEach(System.out::println);
    }
    
    // c) Eliminar los alimentos que tengan cantidad 0
    public void eliminarConCantidadCero() {
        ArrayList<Alimento> alimentos = leer();
        int tamInicial = alimentos.size();
        
        alimentos.removeIf(a -> a.getCantidad() == 0);
        
        int eliminados = tamInicial - alimentos.size();
        guardar(alimentos);
        System.out.println("\n=== c) ELIMINAR ALIMENTOS CON CANTIDAD 0 ===");
        System.out.println("Eliminados " + eliminados + " alimentos con cantidad 0.");
    }
    
    // d) Buscar los alimentos ya vencidos.
    public void buscarVencidos() {
        ArrayList<Alimento> alimentos = leer();
        System.out.println("\n=== d) ALIMENTOS VENCIDOS A FECHA DE HOY ===");
        
        alimentos.stream()
                .filter(Alimento::estaVencido)
                .forEach(System.out::println);
    }
    
    // e) Mostrar el alimento que tenga más cantidad en el refri.
    public void mostrarConMasCantidad() {
        ArrayList<Alimento> alimentos = leer();
        if (alimentos.isEmpty()) {
            System.out.println("No hay alimentos.");
            return;
        }
        
        Alimento maxCantidad = alimentos.stream()
                .max(Comparator.comparingInt(Alimento::getCantidad))
                .orElse(null);
        
        System.out.println("\n=== e) ALIMENTO CON MÁS CANTIDAD ===");
        System.out.println(maxCantidad);
    }
}